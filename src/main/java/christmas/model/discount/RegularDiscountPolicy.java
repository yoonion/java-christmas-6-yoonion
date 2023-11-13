package christmas.model.discount;

import christmas.model.menu.MenuItem;
import christmas.model.order.Orders;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegularDiscountPolicy {
    private static final int DISCOUNT_MINIMUM_DATE = 1;
    private static final int DISCOUNT_MAXIMUM_DATE = 31;

    private static final String GIFT_MENU = "샴페인";
    private static final int GIFT_MENU_MINIMUM_PRICE = 120000;

    private static final int DAY_OF_WEEK_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final List<Integer> SPECIAL_DISCOUNT_DATE = new ArrayList<>(Arrays.asList(3, 10, 17, 24, 25, 31));

    public int applyWeekdayDiscountPrice(int visitDate, Orders orders) {
        // 평일 할인 - 디저트 메뉴 개당 2,023원 할인
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        if (dayOfWeekNumber >= 1 && dayOfWeekNumber <= 4 || dayOfWeekNumber == 7) {
            int dessertMenuQuantity = MenuItem.getDessertMenuQuantity(orders);
            return DAY_OF_WEEK_DISCOUNT_PRICE * dessertMenuQuantity;
        }
        return 0;
    }

    public int applyFreeDayDiscountPrice(int visitDate, Orders orders) {
        // 주말 할인 - 메인 메뉴 개당 2,023원 할인
        int dayOfWeekNumber = getDayOfWeekNumber(visitDate);
        if (dayOfWeekNumber == 5 || dayOfWeekNumber == 6) {
            int mainMenuQuantity = MenuItem.getMainMenuQuantity(orders);
            return DAY_OF_WEEK_DISCOUNT_PRICE * mainMenuQuantity;
        }
        return 0;
    }

    public int applySpecialDiscountPrice(int visitDate) {
        if (SPECIAL_DISCOUNT_DATE.contains(visitDate)) {
            return SPECIAL_DISCOUNT_PRICE;
        }
        return 0;
    }

    public String applyGiftMenuName(int visitDate, int originalTotalPrice) {
        if (visitDate >= DISCOUNT_MINIMUM_DATE &&
                visitDate <= DISCOUNT_MAXIMUM_DATE &&
                originalTotalPrice >= GIFT_MENU_MINIMUM_PRICE) {
            return GIFT_MENU;
        }
        return "";
    }

    public int applyGiftMenuPrice(String menuName) {
        if (!menuName.isEmpty()) {
            return MenuItem.getMenuPrice(menuName);
        }
        return 0;
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