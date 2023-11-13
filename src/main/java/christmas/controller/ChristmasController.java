package christmas.controller;

import christmas.model.discount.ChristmasDiscountPolicy;
import christmas.model.discount.RegularDiscountPolicy;
import christmas.model.menu.MenuItem;
import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import christmas.validator.OrderInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasDiscountPolicy christmasDiscountPolicy;
    private final RegularDiscountPolicy regularDiscountPolicy;
    private final ChristmasService christmasService;
    private final VisitDateInputValidator visitDateInputValidator;
    private final OrderInputValidator orderInputValidator;

    public ChristmasController(
            InputView inputView,
            OutputView outputView,
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

    // 게임 진행 총괄 메서드
    public void promotionRun() {
        int visitDate = inputVisitDate();
        Orders orders = inputOrderMenuAndQuantity();
        outputView.printEventIntroduction(visitDate);
        outputView.printOrders(orders);

        // 할인 전 총주문 금액
        int originalTotalPrice = MenuItem.getOrderTotalPrice(orders);
        outputView.printOriginalTotalPrice(originalTotalPrice);

        // 크리스마스 디데이 할인
        int christmasDiscountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, originalTotalPrice);

        // 평일 할인
        int weekdayDiscountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);

        // 주말 할인
        int freeDayDiscountPrice = regularDiscountPolicy.applyFreeDayDiscountPrice(visitDate, orders);

        // 특별 할인
        // 증정 이벤트
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
