package christmas.model.discount;

public class ChristmasDiscountPolicy {

    private static final int MINIMUM_DISCOUNT_DATE = 1;
    private static final int MAXIMUM_DISCOUNT_DATE = 25;
    private static final int DAILY_DISCOUNT_INCREASE_PRICE = 100;

    private int discountPrice = 1000;

    public int applyChristmasDiscountPrice(int visitDate, int originalTotalPrice) {
        if (visitDate >= MINIMUM_DISCOUNT_DATE && visitDate <= MAXIMUM_DISCOUNT_DATE &&
                originalTotalPrice >= DiscountPolicyConstants.DISCOUNT_ORDER_MINIMUM_PRICE) {
            return discountPrice += (visitDate - MINIMUM_DISCOUNT_DATE) * DAILY_DISCOUNT_INCREASE_PRICE;
        }
        return 0;
    }
}
