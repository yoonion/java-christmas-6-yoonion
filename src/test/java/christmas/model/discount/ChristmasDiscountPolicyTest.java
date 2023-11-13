package christmas.model.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountPolicyTest {

    ChristmasDiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();

    @DisplayName("주문 금액이 10,000원 이상 일 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @CsvSource({"1, 10000", "2, 10000", "7, 10000", "15, 10000", "20, 10000", "25, 10000"})
    void christmasDiscountPriceTest(int visitDate, int originalOrderTotalPrice) {
        int expectPrice = 1000 + ((visitDate - 1) * 100); // 1일 1,000원 2일 1,100원 ... 1일 증가할 때마다 100원씩 증가
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, originalOrderTotalPrice);

        Assertions.assertThat(discountPrice).isEqualTo(expectPrice);
    }

    @DisplayName("주문 금액이 10,000원 미만 일 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @CsvSource({"1, 0", "1, 1000", "1, 5000", "1, 9999"})
    void christmasNotDiscountPriceTest(int visitDate, int originalOrderTotalPrice) {
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, originalOrderTotalPrice);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("이벤트 날짜가 아닐 때 크리스마스 할인 정책 테스트")
    @ParameterizedTest
    @CsvSource({"26, 10000", "27, 10000", "28, 10000", "29, 10000", "30, 10000", "31, 10000"})
    void notRangeChristmasEventDateTest(int visitDate, int originalOrderTotalPrice) {
        int discountPrice = christmasDiscountPolicy.applyChristmasDiscountPrice(visitDate, originalOrderTotalPrice);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}