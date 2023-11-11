package christmas.controller;

import christmas.view.InputView;

public class ChristmasController {

    private final InputView inputView;

    public ChristmasController(InputView inputView) {
        this.inputView = inputView;
    }

    public void promotionRun() {
        inputView.inputVisitDate();
        inputView.inputOrderMenuAndQuantity();
    }

}
