package christmas.controller;

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
    private final ChristmasService christmasService;
    private final VisitDateInputValidator visitDateInputValidator;
    private final OrderInputValidator orderInputValidator;

    public ChristmasController(
            InputView inputView,
            OutputView outputView,
            ChristmasService christmasService,
            VisitDateInputValidator visitDateInputValidator,
            OrderInputValidator orderInputValidator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
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
        outputView.printOriginalTotalPrice(MenuItem.getOrderTotalPrice(orders));
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
