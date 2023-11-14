package christmas.model.discount;

import christmas.model.menu.MenuItem;
import christmas.model.order.Orders;

public class ChristmasDiscountPolicy {

    private static final int MINIMUM_DISCOUNT_DATE = 1;
    private static final int MAXIMUM_DISCOUNT_DATE = 25;
    private static final int DAILY_DISCOUNT_INCREASE_PRICE = 100;

    private int discountPrice = 1000;

    public int applyChristmasDiscountPrice(int visitDate, Orders orders) {
        if (visitDate >= MINIMUM_DISCOUNT_DATE && visitDate <= MAXIMUM_DISCOUNT_DATE &&
                MenuItem.getOrderTotalPrice(orders) >= DiscountPolicyConstants.DISCOUNT_ORDER_MINIMUM_PRICE) {
            return discountPrice += (visitDate - MINIMUM_DISCOUNT_DATE) * DAILY_DISCOUNT_INCREASE_PRICE;
        }
        return 0;
    }
}
