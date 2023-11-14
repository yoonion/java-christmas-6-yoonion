package christmas.model.discount;

import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ChristmasDiscountPolicyTest {

    ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
    ChristmasService christmasService = new ChristmasService();

    // 크리스마스 디데이 할인에 포함된 날짜
    static Stream<Arguments> christmasDiscountDayProvider(){
        return Stream.of(
                arguments(1), arguments(2), arguments(7), arguments(15), arguments(20), arguments(25)
        );
    }

    // 크리스마스 디데이 할인이 아닌 날짜
    static Stream<Arguments> notChristmasDiscountDayProvider(){
        return Stream.of(
                arguments(26), arguments(27), arguments(28), arguments(29), arguments(30), arguments(31)
        );
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