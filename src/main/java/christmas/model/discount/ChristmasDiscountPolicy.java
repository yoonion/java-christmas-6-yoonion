package christmas.model.discount;

public class ChristmasDiscountPolicy {

    private static final int MINIMUM_DISCOUNT_DATE = 1;
    private static final int MAXIMUM_DISCOUNT_DATE = 25;
    private static final int DAILY_DISCOUNT_INCREASE_PRICE = 100;

    private int discountPrice = 1000;

    public int applyDiscount(int visitDate, String orderMenuAndQuantity) {
        return discountPrice += (visitDate - MINIMUM_DISCOUNT_DATE) * DAILY_DISCOUNT_INCREASE_PRICE;
    }
}
