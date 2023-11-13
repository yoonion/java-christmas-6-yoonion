package christmas.model.badge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @DisplayName("총혜택 금액이 5,000원 이상 10,000원 미만 일 때 따른 배지 이름(별) 가져오기")
    @ParameterizedTest
    @ValueSource(ints = {5000, 5001, 6000, 9000, 9999})
    void getEventBadgeNameStarTest(int totalDiscountedPrice) {
        String eventBadgeName = Badge.getEventBadgeName(totalDiscountedPrice);

        Assertions.assertThat(eventBadgeName).isEqualTo("별");
    }

    @DisplayName("총혜택 금액이 10,000원 이상 20,000원 미만일 때 배지 이름(트리) 가져오기")
    @ParameterizedTest
    @ValueSource(ints = {10000, 10001, 12000, 15000, 19999})
    void getEventBadgeNameTreeTest(int totalDiscountedPrice) {
        String eventBadgeName = Badge.getEventBadgeName(totalDiscountedPrice);

        Assertions.assertThat(eventBadgeName).isEqualTo("트리");
    }

    @DisplayName("총혜택 금액이 20,000원 이상 일 때 배지 이름(트리) 가져오기")
    @ParameterizedTest
    @ValueSource(ints = {20000, 20001, 100000, 5000000, 9999999})
    void getEventBadgeNameSantaTest(int totalDiscountedPrice) {
        String eventBadgeName = Badge.getEventBadgeName(totalDiscountedPrice);

        Assertions.assertThat(eventBadgeName).isEqualTo("산타");
    }
}