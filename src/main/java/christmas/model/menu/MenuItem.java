package christmas.model.menu;

import christmas.model.order.Orders;

import java.util.Map;

public enum MenuItem {
    YANG_SUNG_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIB("바비큐립", 54000, MenuCategory.MAIN),
    SEA_FOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),

    CHOCO_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuCategory.DESSERT),

    ZERO_COLA("제로콜라", 3000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuCategory.BEVERAGE),

    EMPTY("없음", 0, MenuCategory.EMPTY);

    private final String itemName;
    private final int itemPrice;
    private final MenuCategory category;

    MenuItem(String itemName, int itemPrice, MenuCategory category) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.category = category;
    }

    public static MenuItem findByItemName(String itemName) {
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.itemName.equals(itemName)) {
                return menuItem;
            }
        }
        return MenuItem.EMPTY;
    }

    public static boolean isMenuBeverage(String itemName) {
        MenuItem findItem = findByItemName(itemName);
        return findItem.category == MenuCategory.BEVERAGE;
    }

    public static boolean hasItemName(String itemName) {
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.itemName.equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static int getOrderTotalPrice(Orders orders) {
        Map<String, Integer> orderItems = orders.getOrders();
        int orderTotalPrice = 0;
        for (Map.Entry<String, Integer> order : orderItems.entrySet()) {
            orderTotalPrice += (findByItemName(order.getKey()).itemPrice) * order.getValue();
        }
        return orderTotalPrice;
    }

    public static int getDessertMenuQuantity(Orders orders) {
        Map<String, Integer> orderItems = orders.getOrders();
        int dessertMenuQuantity = 0;
        for (Map.Entry<String, Integer> order : orderItems.entrySet()) {
            if (findByItemName(order.getKey()).category == MenuCategory.DESSERT) {
                dessertMenuQuantity += order.getValue();
            }
        }
        return dessertMenuQuantity;
    }

    public static int getMainMenuQuantity(Orders orders) {
        Map<String, Integer> orderItems = orders.getOrders();
        int mainMenuQuantity = 0;
        for (Map.Entry<String, Integer> order : orderItems.entrySet()) {
            if (findByItemName(order.getKey()).category == MenuCategory.MAIN) {
                mainMenuQuantity += order.getValue();
            }
        }
        return mainMenuQuantity;
    }

    public static int getMenuPrice(String itemName) {
        return findByItemName(itemName).itemPrice;
    }
}
