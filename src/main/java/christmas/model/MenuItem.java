package christmas.model;

public enum MenuItem {
    YANGSUNGSOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESARSALAD("시저샐러드", 8000, MenuCategory.APPETIZER),

    TIBONESTEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQRIB("바비큐립", 54000, MenuCategory.MAIN),
    SEAFOODPASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMASPASTA("크리스마스파스타", 25000, MenuCategory.MAIN),

    CHOCOCAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICECREAM("아이스크림", 5000, MenuCategory.DESSERT),
    
    ZEROCOLA("제로콜라", 3000, MenuCategory.BEVERAGE),
    REDWINE("레드와인", 60000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuCategory.BEVERAGE);

    private String itemName;
    private int itemPrice;
    private MenuCategory category;

    MenuItem(String itemName, int itemPrice, MenuCategory category) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.category = category;
    }
}
