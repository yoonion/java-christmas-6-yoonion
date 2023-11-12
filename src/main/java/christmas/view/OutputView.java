package christmas.view;

import christmas.model.order.Orders;

import java.util.Map;

public class OutputView {

    public void printEventIntroduction(int visitDate) {
        String introductionMessage = "12월 " + visitDate + "일에 우테코 식당에 받을 이벤트 혜택 미리보기!";
        System.out.println(introductionMessage);
    }

    public void printOrders(Orders orders) {
        System.out.println("\n<주문 메뉴>");
        Map<String, Integer> orderItems = orders.getOrders();
        for (Map.Entry<String, Integer> orderItem : orderItems.entrySet()) {
            System.out.println(orderItem.getKey() + " " + orderItem.getValue() + "개");
        }
    }
}
