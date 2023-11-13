package christmas.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateInputValidatorTest {
    VisitDateInputValidator visitDateInputValidator = new VisitDateInputValidator();

    @DisplayName("입력한 방문 날짜가 숫자가 아닌 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "1ab", "안녕", "*&^"})
    void checkStringIsNumberTest(String visitDate) {
        Assertions.assertThatThrownBy(() -> visitDateInputValidator.checkStringIsNumber(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_DATE_MESSAGE);
    }

    @DisplayName("입력한 방문 날짜가 숫자의 범위가 1~31이 아닐 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "50", "1000"})
    void checkVisitDateRangeTest(String visitDate) {
        Assertions.assertThatThrownBy(() -> visitDateInputValidator.checkVisitDateRange(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ValidatorConstants.INVALID_DATE_MESSAGE);
    }
}