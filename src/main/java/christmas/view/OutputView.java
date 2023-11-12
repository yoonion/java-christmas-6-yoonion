package christmas.view;

public class OutputView {

    public void printEventIntroduction(int visitDate) {
        String introductionMessage = "12월 " + Integer.toString(visitDate) + "일에 우테코 식당에 받을 이벤트 혜택 미리보기!";
        System.out.println(introductionMessage);
    }
}
