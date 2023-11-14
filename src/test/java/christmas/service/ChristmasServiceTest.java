package christmas.service;

import christmas.validator.ValidatorConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

class ChristmasServiceTest {

    ChristmasService christmasService = new ChristmasService();

    @DisplayName("주문 객체 생성 - 음료만 주문 했을 경우 예외 처리")
    @Test
    void createOrderOnlyBeverageTest() {
        Assertions.assertThatThrownBy(() -> christmasService.createOrders("제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_ORDER_MESSAGE);
    }

    @DisplayName("주문 객체 생성 - 없는 메뉴 주문 했을 경우 예외 처리")
    @Test
    void createOrderNotExistMenuTest() {
        Assertions.assertThatThrownBy(() -> christmasService.createOrders("안녕-1,하세요-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_ORDER_MESSAGE);
    }

    @DisplayName("주문 객체 생성 - 총 주문 개수가 1~20개가 아닌 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-0,제로콜라-0", "티본스테이크-21"})
    void createOrderQuantityRangeTest(String orderMenuAndQuantity) {
        Assertions.assertThatThrownBy(() -> christmasService.createOrders("티본스테이크-0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_ORDER_MESSAGE);
    }

    @DisplayName("할인 혜택 내역 테스트")
    @Test
    void getDiscountDetailsTest() {
        int christmasDiscountPrice = 0;
        int weekdayDiscountPrice = 1000;
        int freeDayDiscountPrice = 1000;
        int specialDiscountPrice = 0;
        int giftMenuDiscountPrice = 25000;
        Map<String, Integer> discountDetails = christmasService.getDiscountDetails(
                christmasDiscountPrice,
                weekdayDiscountPrice,
                freeDayDiscountPrice,
                specialDiscountPrice,
                giftMenuDiscountPrice
        );
        Assertions.assertThat(discountDetails.size()).isEqualTo(3);
    }

    @DisplayName("총혜택 금액 테스트")
    @Test
    void getTotalDiscountPriceTest() {
        int christmasDiscountPrice = 0;
        int weekdayDiscountPrice = 1000;
        int freeDayDiscountPrice = 1000;
        int specialDiscountPrice = 0;
        int giftMenuDiscountPrice = 25000;
        Map<String, Integer> discountDetails = christmasService.getDiscountDetails(
                christmasDiscountPrice,
                weekdayDiscountPrice,
                freeDayDiscountPrice,
                specialDiscountPrice,
                giftMenuDiscountPrice
        );
        int totalDiscountPrice = christmasService.getTotalDiscountPrice(discountDetails);
        Assertions.assertThat(totalDiscountPrice).isEqualTo(27000);
    }

    @DisplayName("할인 후 결제 금액 테스트")
    @Test
    void getDiscountedPaymentPriceTest() {
        int christmasDiscountPrice = 0;
        int weekdayDiscountPrice = 1000;
        int freeDayDiscountPrice = 1000;
        int specialDiscountPrice = 0;
        int giftMenuDiscountPrice = 25000;
        Map<String, Integer> discountDetails = christmasService.getDiscountDetails(
                christmasDiscountPrice,
                weekdayDiscountPrice,
                freeDayDiscountPrice,
                specialDiscountPrice,
                giftMenuDiscountPrice
        );
        int totalDiscountPrice = christmasService.getTotalDiscountPrice(discountDetails);
        int discountedPaymentPrice = christmasService.getDiscountedPaymentPrice(
                100000, totalDiscountPrice, giftMenuDiscountPrice
        );
        Assertions.assertThat(discountedPaymentPrice).isEqualTo(98000);
    }
}