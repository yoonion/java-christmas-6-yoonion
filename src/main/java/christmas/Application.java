package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        ChristmasController christmasController = new ChristmasController(inputView);
        christmasController.promotionRun();
    }
}
