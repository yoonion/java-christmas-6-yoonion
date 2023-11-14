package christmas.model.badge;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String badgeName;
    private final int discountedPrice;

    Badge(String badgeName, int discountedPrice) {
        this.badgeName = badgeName;
        this.discountedPrice = discountedPrice;
    }

    public static String getEventBadgeName(int totalDiscountedPrice) {
        String badgeName = "";
        int nowBadgePrice = 0;
        for (Badge badge : Badge.values()) {
            if (badge.discountedPrice <= totalDiscountedPrice && badge.discountedPrice >= nowBadgePrice) {
                badgeName = badge.badgeName;
                nowBadgePrice = badge.discountedPrice;
            }
        }
        return badgeName;
    }
}
