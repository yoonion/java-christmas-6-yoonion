package christmas.view;

import christmas.model.order.Orders;

import java.util.Map;

public class OutputView {

    private static final String NOTING_MESSAGE = "없음";
    private static final String ORDER_MENU_TITLE = "\n<주문 메뉴>";
    private static final String ORIGINAL_TOTAL_PRICE_TITLE = "\n<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "\n<증정 메뉴>";
    private static final String DISCOUNT_PRICE_DETAILS_TITLE = "\n<혜택 내역>";
    private static final String TOTAL_DISCOUNT_PRICE_TITLE = "\n<총혜택 금액>";
    private static final String DISCOUNTED_PAYMENT_PRICE = "\n<할인 후 예상 결제 금액>";

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
        System.out.println(ORIGINAL_TOTAL_PRICE_TITLE);
        System.out.println(getFormatCommaNumber(orderTotalPrice));
    }

    public void printGiftMenuName(String giftMenu) {
        System.out.println(GIFT_MENU_TITLE);
        if (!giftMenu.isEmpty()) {
            System.out.println(giftMenu + " 1개");
        } else if (giftMenu.isEmpty()) {
            System.out.println(NOTING_MESSAGE);
        }
    }

    public void printDiscountPriceDetails(Map<String, Integer> discountDetails) {
        System.out.println(DISCOUNT_PRICE_DETAILS_TITLE);
        if (discountDetails.isEmpty()) {
            System.out.println(NOTING_MESSAGE);
        } else if (!discountDetails.isEmpty()) {
            for (Map.Entry<String, Integer> discountDetail : discountDetails.entrySet()) {
                String discountEventName = discountDetail.getKey();
                Integer discountPrice = discountDetail.getValue();
                String discountPriceFormat = getFormatCommaNumber(discountPrice * -1);
                System.out.println(discountEventName + ": " + discountPriceFormat + "원");
            }
        }
    }

    public void printTotalDiscountPrice() {
        System.out.println(TOTAL_DISCOUNT_PRICE_TITLE);
    }

    private static String getFormatCommaNumber(int number) {
        return Integer.toString(number).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
    }
}
