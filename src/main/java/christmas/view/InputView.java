package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INTRODUCTION_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String VISIT_DATE_QUESTION_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String inputVisitDate() {
        System.out.println(INTRODUCTION_MESSAGE);
        System.out.println(VISIT_DATE_QUESTION_MESSAGE);
        return Console.readLine();
    }

    public String inputOrderMenuAndQuantity() {
        System.out.println(ORDER_MENU_MESSAGE);
        return Console.readLine();
    }
}
