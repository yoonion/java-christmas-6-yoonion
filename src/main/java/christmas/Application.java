package christmas;

import christmas.controller.ChristmasController;
import christmas.validator.OrderInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        VisitDateInputValidator visitDateInputValidator = new VisitDateInputValidator();
        OrderInputValidator orderInputValidator = new OrderInputValidator();

        ChristmasController christmasController = new ChristmasController(
                inputView, visitDateInputValidator, orderInputValidator
        );
        christmasController.promotionRun();
    }
}