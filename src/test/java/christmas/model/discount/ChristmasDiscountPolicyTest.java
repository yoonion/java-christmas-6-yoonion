package christmas.model.discount;

import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ChristmasDiscountPolicyTest {

    ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
    ChristmasService christmasService = new ChristmasService();

    // 크리스마스 디데이 할인에 포함된 날짜
    private static Stream<Integer> christmasDiscountDayProvider() {
        return Stream.of(1, 2, 7, 15, 20, 25);
    }

    // 크리스마스 디데이 할인이 아닌 날짜
    private static Stream<Integer> notChristmasDiscountDayProvider(){
        return Stream.of(26, 27, 28, 29, 30, 31);
    }

    @DisplayName("주문 금액이 10,000원 이상 일 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @MethodSource("christmasDiscountDayProvider")
    void christmasDiscountPriceTest(int visitDate) {
        int expectPrice = 1000 + ((visitDate - 1) * 100); // 1일 1,000원 2일 1,100원 ... 1일 증가할 때마다 100원씩 증가
        Orders orders = christmasService.createOrders("티본스테이크-1");
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(expectPrice);
    }

    @DisplayName("주문 금액이 10,000원 미만 일 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @MethodSource("christmasDiscountDayProvider")
    void christmasNotDiscountPriceTest(int visitDate) {
        Orders orders = christmasService.createOrders("양송이수프-1");
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("이벤트 날짜가 아닐 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @MethodSource("notChristmasDiscountDayProvider")
    void notRangeChristmasEventDateTest(int visitDate) {
        Orders orders = christmasService.createOrders("티본스테이크-1");
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, orders);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}