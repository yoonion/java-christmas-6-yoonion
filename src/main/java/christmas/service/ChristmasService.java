package christmas.service;

import christmas.model.order.Orders;

import java.util.HashMap;
import java.util.Map;

public class ChristmasService {

    public Orders createOrders(String inputOrderMenuAndQuantity) {
        return new Orders(orderConvertStringToMap(inputOrderMenuAndQuantity));
    }

    public int getTotalDiscountPrice(Map<String, Integer> discountDetails) {
        int totalDiscountPrice = 0;
        for (Integer discountPrice : discountDetails.values()) {
            totalDiscountPrice += discountPrice;
        }
        return totalDiscountPrice;
    }

    public Map<String, Integer> getDiscountDetails(
            int christmasDiscountPrice, int weekdayDiscountPrice,
            int freeDayDiscountPrice, int specialDiscountPrice, int giftMenuPrice) {
        Map<String, Integer> discountDetails = new HashMap<>();
        if (christmasDiscountPrice > 0) {
            discountDetails.put("크리스마스 디데이 할인", christmasDiscountPrice);
        }
        if (weekdayDiscountPrice > 0) {
            discountDetails.put("평일 할인", weekdayDiscountPrice);
        }
        if (freeDayDiscountPrice > 0) {
            discountDetails.put("주말 할인", freeDayDiscountPrice);
        }
        if (specialDiscountPrice > 0) {
            discountDetails.put("특별 할인", specialDiscountPrice);
        }
        if (giftMenuPrice > 0) {
            discountDetails.put("증정 이벤트", giftMenuPrice);
        }
        return discountDetails;
    }

    private Map<String, Integer> orderConvertStringToMap(String inputOrderMenuAndQuantity) {
        Map<String, Integer> orders = new HashMap<>();
        String[] orderMenuAndQuantities = inputOrderMenuAndQuantity.split(",");
        for (String orderMenuAndQuantity : orderMenuAndQuantities) {
            String orderMenu = orderMenuAndQuantity.split("-")[0];
            Integer orderQuantity = Integer.parseInt(orderMenuAndQuantity.split("-")[1]);
            orders.put(orderMenu, orderQuantity);
        }
        return orders;
    }

    public int getDiscountedPaymentPrice(int originalTotalPrice, int totalDiscountPrice, int giftMenuPrice) {
        return originalTotalPrice - totalDiscountPrice + giftMenuPrice;
    }
}
