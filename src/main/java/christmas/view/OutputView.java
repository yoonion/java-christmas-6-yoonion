package christmas.view;

import christmas.model.order.Orders;

import java.util.Map;

public class OutputView {

    private static final String ORDER_MENU_TITLE = "\n<주문 메뉴>";
    private static final String ORIGINAL_TOTAL_PRICE_TITLE = "\n<할인 전 총주문 금액>";

    public void printEventIntroduction(int visitDate) {
        String introductionMessage = "12월 " + visitDate + "일에 우테코 식당에 받을 이벤트 혜택 미리보기!";
        System.out.println(introductionMessage);
    }

    public void printOrders(Orders orders) {
        System.out.println(ORDER_MENU_TITLE);
        Map<String, Integer> orderItems = orders.getOrders();
        for (Map.Entry<String, Integer> orderItem : orderItems.entrySet()) {
            System.out.println(orderItem.getKey() + " " + orderItem.getValue() + "개");
        }
    }

    public void printOriginalTotalPrice(int orderTotalPrice) {
        String originalTotalPrice = Integer.toString(orderTotalPrice);
        System.out.println(ORIGINAL_TOTAL_PRICE_TITLE);
        System.out.println(originalTotalPrice.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
    }
}
