package christmas.controller;

import christmas.model.badge.Badge;
import christmas.model.discount.ChristmasDiscountPolicy;
import christmas.model.discount.RegularDiscountPolicy;
import christmas.model.menu.MenuItem;
import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import christmas.validator.OrderInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasDiscountPolicy christmasDiscountPolicy;
    private final RegularDiscountPolicy regularDiscountPolicy;
    private final ChristmasService christmasService;
    private final VisitDateInputValidator visitDateInputValidator;
    private final OrderInputValidator orderInputValidator;

    public ChristmasController(
            InputView inputView, OutputView outputView,
            ChristmasDiscountPolicy christmasDiscountPolicy,
            RegularDiscountPolicy regularDiscountPolicy,
            ChristmasService christmasService,
            VisitDateInputValidator visitDateInputValidator,
            OrderInputValidator orderInputValidator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasDiscountPolicy = christmasDiscountPolicy;
        this.regularDiscountPolicy = regularDiscountPolicy;
        this.christmasService = christmasService;
        this.visitDateInputValidator = visitDateInputValidator;
        this.orderInputValidator = orderInputValidator;
    }

    // 이벤트 진행 메서드
    public void promotionRun() {
        int visitDate = inputVisitDate();
        Orders orders = inputOrderMenuAndQuantity();
        outputView.printEventIntroduction(visitDate);

        // <주문 메뉴>, <할인 전 총주문 금액>
        int originalTotalPrice = printOrderDetails(orders);
        // <증정 메뉴>
        String giftMenu = printGiftMenu(visitDate, orders);
        // <혜택 내역>
        Map<String, Integer> discountDetails = printDiscountDetails(visitDate, originalTotalPrice, orders, giftMenu);
        // <총혜택 금액>
        int totalDiscountPrice = printTotalDiscountPrice(discountDetails);
        // <할인 후 예상 결제 금액>
        printAfterDiscountPaymentPrice(originalTotalPrice, totalDiscountPrice, MenuItem.getMenuPrice(giftMenu));
        // <12월 이벤트 배지>
        printEventBadge(totalDiscountPrice);
    }

    private Map<String, Integer> printDiscountDetails(int visitDate, int originalTotalPrice, Orders orders, String giftMenu) {
        int christmasDiscountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, originalTotalPrice);
        int weekdayDiscountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);
        int freeDayDiscountPrice = regularDiscountPolicy.applyFreeDayDiscountPrice(visitDate, orders);
        int specialDiscountPrice = regularDiscountPolicy.applySpecialDiscountPrice(visitDate, orders);
        int giftMenuPrice = regularDiscountPolicy.applyGiftMenuPrice(giftMenu);
        Map<String, Integer> discountDetails = christmasService.getDiscountDetails(
                christmasDiscountPrice, weekdayDiscountPrice, freeDayDiscountPrice, specialDiscountPrice, giftMenuPrice
        );
        outputView.printDiscountPriceDetails(discountDetails);
        return discountDetails;
    }

    private void printEventBadge(int totalDiscountPrice) {
        String eventBadgeName = Badge.getEventBadgeName(totalDiscountPrice);
        outputView.printEventBadgeName(eventBadgeName);
    }

    private String printGiftMenu(int visitDate, Orders orders) {
        String giftMenu = regularDiscountPolicy.getGiftMenuName(visitDate, orders);
        outputView.printGiftMenuName(giftMenu);
        return giftMenu;
    }

    private void printAfterDiscountPaymentPrice(int originalTotalPrice, int totalDiscountPrice, int giftMenuPrice) {
        int discountedPaymentPrice = christmasService.getDiscountedPaymentPrice(
                originalTotalPrice, totalDiscountPrice, giftMenuPrice
        );
        outputView.printDiscountedPaymentPrice(discountedPaymentPrice);
    }

    private int printTotalDiscountPrice(Map<String, Integer> discountDetails) {
        int totalDiscountPrice = christmasService.getTotalDiscountPrice(discountDetails);
        outputView.printTotalDiscountPrice(totalDiscountPrice);
        return totalDiscountPrice;
    }

    private int printOrderDetails(Orders orders) {
        outputView.printOrders(orders);

        int originalTotalPrice = MenuItem.getOrderTotalPrice(orders);
        outputView.printOriginalTotalPrice(originalTotalPrice);
        return originalTotalPrice;
    }

    private int inputVisitDate() {
        while (true) {
            try {
                String inputVisitDate = inputView.inputVisitDate();
                visitDateInputValidator.checkStringIsNumber(inputVisitDate);
                visitDateInputValidator.checkVisitDateRange(inputVisitDate);
                return Integer.parseInt(inputVisitDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Orders inputOrderMenuAndQuantity() {
        while (true) {
            try {
                String inputOrderMenuAndQuantity = inputView.inputOrderMenuAndQuantity();
                orderInputValidator.checkOrderFormat(inputOrderMenuAndQuantity);
                return christmasService.createOrders(inputOrderMenuAndQuantity);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
