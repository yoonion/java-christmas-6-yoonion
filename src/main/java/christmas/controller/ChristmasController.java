package christmas.controller;

import christmas.validator.OrderInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;

public class ChristmasController {

    private final InputView inputView;
    private final VisitDateInputValidator visitDateInputValidator;
    private final OrderInputValidator orderInputValidator;

    public ChristmasController(
            InputView inputView,
            VisitDateInputValidator visitDateInputValidator,
            OrderInputValidator orderInputValidator
    ) {
        this.inputView = inputView;
        this.visitDateInputValidator = visitDateInputValidator;
        this.orderInputValidator = orderInputValidator;
    }

    // 게임 진행 총괄 메서드
    public void promotionRun() {
        String inputVisitDate = inputVisitDate();
        String inputOrderMenuAndQuantity = inputOrderMenuAndQuantity();
    }

    private String inputVisitDate() {
        while (true) {
            try {
                String inputVisitDate = inputView.inputVisitDate();
                visitDateInputValidator.checkStringIsNumber(inputVisitDate);
                visitDateInputValidator.checkVisitDateRange(inputVisitDate);
                return inputVisitDate;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String inputOrderMenuAndQuantity() {
        while (true) {
            try {
                String inputOrderMenuAndQuantity = inputView.inputOrderMenuAndQuantity();
                orderInputValidator.checkOrderFormat(inputOrderMenuAndQuantity);
                return inputOrderMenuAndQuantity;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
