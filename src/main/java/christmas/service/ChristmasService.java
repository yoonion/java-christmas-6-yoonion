package christmas.service;

import christmas.model.discount.ChristmasDiscountPolicy;
import christmas.model.discount.RegularDiscountPolicy;
import christmas.model.order.Orders;

import java.util.HashMap;
import java.util.Map;

public class ChristmasService {

    ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
    RegularDiscountPolicy regularDiscountPolicy = new RegularDiscountPolicy();

    public Orders createOrders(String inputOrderMenuAndQuantity) {
        return new Orders(orderConvertStringToMap(inputOrderMenuAndQuantity));
    }

    public int getChristmasDiscountPrice(int visitDate, int originalTotalPrice) {
        return christmasDiscountPolicy.getChristmasDiscountPrice(visitDate, originalTotalPrice);
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
}
