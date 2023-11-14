package christmas.service;

import christmas.validator.ValidatorConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}