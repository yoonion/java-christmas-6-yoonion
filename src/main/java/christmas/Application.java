package christmas;

import christmas.controller.ChristmasController;
import christmas.validator.OrderInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        VisitDateInputValidator visitDateInputValidator = new VisitDateInputValidator();
        OrderInputValidator orderInputValidator = new OrderInputValidator();

        ChristmasController christmasController = new ChristmasController(
                inputView, outputView, visitDateInputValidator, orderInputValidator
        );
        christmasController.promotionRun();
    }
}