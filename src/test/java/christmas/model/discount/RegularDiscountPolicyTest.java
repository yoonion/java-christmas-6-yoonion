package christmas.model.discount;

import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RegularDiscountPolicyTest {

    RegularDiscountPolicy regularDiscountPolicy = new RegularDiscountPolicy();
    ChristmasService christmasService = new ChristmasService();

    @DisplayName("평일 주문에 총 주문 금액이 10,000원 미만 인 경우, 할인 해주지 않음")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekdayMinimumOrderPriceTest(int visitDate) {
        Orders orders = christmasService.createOrders("아이스크림-1");
        int discountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("평일 주문에 디저트가 있는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekdayDessertDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,초코케이크-1");
        int discountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(2023);
    }

    @DisplayName("평일 주문에 디저트가 2개 있는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void weekdayTwoDessertDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,초코케이크-1,아이스크림-1");
        int discountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(4046);
    }

    @DisplayName("평일 주문에 디저트가 없는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void freeDayNotDessertDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applyWeekdayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("주말 주문에 메인 메뉴가 있는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void freeDayMainMenuDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applyFreeDayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(2023);
    }

    @DisplayName("주말 주문에 메인 메뉴가 2개 있는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void freeDayMainTwoMenuDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,바비큐립-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applyFreeDayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(4046);
    }

    @DisplayName("주말 주문에 메인 메뉴가 없는 경우 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void freeDayNotMainMenuDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("초코케이크-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applyFreeDayDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("특별 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void specialDayDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("초코케이크-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applySpecialDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(1000);
    }

    @DisplayName("특별 할인이 아닌 날 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 7, 13, 19, 28, 30})
    void notSpecialDayDiscountTest(int visitDate) {
        Orders orders = christmasService.createOrders("초코케이크-1,양송이수프-1,레드와인-1");
        int discountPrice = regularDiscountPolicy.applySpecialDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 120,000원 이상이면, 샴페인 증정 이벤트")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void giftMenuTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1,바비큐립-1,양송이수프-1,레드와인-1");
        String giftMenuName = regularDiscountPolicy.getGiftMenuName(visitDate, orders);

        Assertions.assertThat(giftMenuName).isEqualTo("샴페인");
    }

    @DisplayName("할인 전 총주문 금액이 120,000원 미만이면, 샴페인 증정 안함")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void notGiftMenuTest(int visitDate) {
        Orders orders = christmasService.createOrders("양송이수프-1");
        String giftMenuName = regularDiscountPolicy.getGiftMenuName(visitDate, orders);

        Assertions.assertThat(giftMenuName).isEqualTo("");
    }

    @DisplayName("증정품 있는 경우, 증정품의 가격 가져오기")
    @Test
    void giftMenuPriceTest() {
        int giftMenuPrice = regularDiscountPolicy.applyGiftMenuPrice("샴페인");

        Assertions.assertThat(giftMenuPrice).isEqualTo(25000);
    }

    @DisplayName("증정품 없는 경우, 증정품의 가격 가져오기")
    @Test
    void notGiftMenuPriceTest() {
        int giftMenuPrice = regularDiscountPolicy.applyGiftMenuPrice("");

        Assertions.assertThat(giftMenuPrice).isEqualTo(0);
    }
}