package christmas.model.discount;

import christmas.model.menu.MenuItem;
import christmas.model.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularDiscountPolicy {
    private static final int DISCOUNT_MINIMUM_DATE = 1;
    private static final int DISCOUNT_MAXIMUM_DATE = 31;

    private static final String GIFT_MENU = "샴페인";
    private static final int MINIMUM_ORDER_PRICE_FOR_GIFT = 120000;

    private static final int DAY_OF_WEEK_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final List<Integer> SPECIAL_DISCOUNT_DATE = new ArrayList<>(Arrays.asList(3, 10, 17, 24, 25, 31));

    public String getGiftMenuName(int visitDate, Orders orders) {
        int orderTotalPrice = MenuItem.getOrderTotalPrice(orders);
        if (visitDate >= DISCOUNT_MINIMUM_DATE &&
                visitDate <= DISCOUNT_MAXIMUM_DATE &&
                orderTotalPrice >= MINIMUM_ORDER_PRICE_FOR_GIFT) {
            return GIFT_MENU;
        }
        return "";
    }

    // 평일 할인 - 디저트 메뉴 개당 2,023원 할인
    public int applyWeekdayDiscountPrice(int visitDate, Orders orders) {
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        if ((dayOfWeekNumber >= 1 && dayOfWeekNumber <= 4 || dayOfWeekNumber == 7) && isMoreMinimumOrderTotalPrice(orders)) {
            int dessertMenuQuantity = MenuItem.getDessertMenuQuantity(orders);
            return DAY_OF_WEEK_DISCOUNT_PRICE * dessertMenuQuantity;
        }
        return 0;
    }

    // 주말 할인 - 메인 메뉴 개당 2,023원 할인
    public int applyFreeDayDiscountPrice(int visitDate, Orders orders) {
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        if ((dayOfWeekNumber == 5 || dayOfWeekNumber == 6) && isMoreMinimumOrderTotalPrice(orders)) {
            int mainMenuQuantity = MenuItem.getMainMenuQuantity(orders);
            return DAY_OF_WEEK_DISCOUNT_PRICE * mainMenuQuantity;
        }
        return 0;
    }

    public int applySpecialDiscountPrice(int visitDate, Orders orders) {
        if (SPECIAL_DISCOUNT_DATE.contains(visitDate) && isMoreMinimumOrderTotalPrice(orders)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return 0;
    }

    public int applyGiftMenuPrice(String menuName) {
        if (!menuName.isEmpty()) {
            return MenuItem.getMenuPrice(menuName);
        }
        return 0;
    }

    private boolean isMoreMinimumOrderTotalPrice(Orders orders) {
        int orderTotalPrice = MenuItem.getOrderTotalPrice(orders);
        return orderTotalPrice >= DiscountPolicyConstants.DISCOUNT_ORDER_MINIMUM_PRICE;
    }

    private static int getDayOfWeekNumber(int visitDate) {
        LocalDate date = LocalDate.of(
                DiscountPolicyConstants.EVENT_YEAR, DiscountPolicyConstants.EVENT_MONTH, visitDate
        );

        // 숫자 요일 구하기 - 월요일부터 일요일까지 1~7의 숫자로 표현됩니다. 1,2,3,4,7(평일), 5,6(주말)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }
}