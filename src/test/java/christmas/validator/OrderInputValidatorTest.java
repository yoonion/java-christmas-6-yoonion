package christmas.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderInputValidatorTest {

    OrderInputValidator orderInputValidator = new OrderInputValidator();

    @DisplayName("주문 메뉴에 하이픈(-) 이 없는 경우 예외 처리")
    @Test
    void orderFormatHyphenTest() {
        Assertions.assertThatThrownBy(() -> orderInputValidator.checkOrderFormat("티본스테이크1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_ORDER_MESSAGE);
    }

    @DisplayName("주문 메뉴에 중복 메뉴가 있는 경우 예외 처리")
    @Test
    void orderDuplicateMenuTest() {
        String order = "티본스테이크-1,티본스테이크-2,제로콜라-1";
        Assertions.assertThatThrownBy(() -> orderInputValidator.checkOrderFormat(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_ORDER_MESSAGE);
    }
}