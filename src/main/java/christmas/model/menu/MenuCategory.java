package christmas.model.menu;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료"),
    EMPTY("없음");

    private final String categoryName;

    MenuCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
