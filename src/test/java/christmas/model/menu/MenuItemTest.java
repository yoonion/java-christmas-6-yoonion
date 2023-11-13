package christmas.model.menu;

import christmas.model.order.Orders;
import christmas.service.ChristmasService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuItemTest {

    ChristmasService christmasService = new ChristmasService();

    @DisplayName("메뉴 이름으로 해당 메뉴 가져오기")
    @Test
    void findByItemNameTest() {
        MenuItem findItem = MenuItem.findByItemName("양송이수프");
        Assertions.assertThat(findItem).isEqualTo(MenuItem.YANG_SUNG_SOUP);
    }

    @DisplayName("없는 메뉴 이름으로 해당 메뉴 찾을 경우")
    @Test
    void notExistFindByItemNameTest() {
        MenuItem findItem = MenuItem.findByItemName("내뱃살");
        Assertions.assertThat(findItem).isEqualTo(MenuItem.EMPTY);
    }

    @DisplayName("메뉴 이름으로 해당 메뉴 가격 가져오기")
    @Test
    void getMenuPriceTest() {
        int itemPrice = MenuItem.getMenuPrice("양송이수프");
        Assertions.assertThat(itemPrice).isEqualTo(6000);
    }

    @DisplayName("없는 메뉴 이름으로 해당 메뉴 가격 가져오기")
    @Test
    void getNotExistMenuPriceTest() {
        int itemPrice = MenuItem.getMenuPrice("슈퍼울트라삼겹살");
        Assertions.assertThat(itemPrice).isEqualTo(0);
    }

    @DisplayName("주문의 할인 전 총합계를 구하기")
    @Test
    void getOrderTotalPriceTest() {
        Orders orders = christmasService.createOrders("티본스테이크-1,양송이수프-1,초코케이크-1");
        int orderTotalPrice = MenuItem.getOrderTotalPrice(orders);
        Assertions.assertThat(orderTotalPrice).isEqualTo(76000);
    }

    @DisplayName("주문의 할인 전 총합계를 구하기 - 메뉴 개수 여러개")
    @Test
    void getOrderTotalPriceManyQuantityTest() {
        Orders orders = christmasService.createOrders("티본스테이크-2,양송이수프-2,초코케이크-2");
        int orderTotalPrice = MenuItem.getOrderTotalPrice(orders);
        Assertions.assertThat(orderTotalPrice).isEqualTo(152000);
    }

    @DisplayName("주문의 디저트 개수 구하기")
    @Test
    void getDessertMenuQuantity() {
        Orders orders = christmasService.createOrders("티본스테이크-1,양송이수프-1,초코케이크-2");
        int dessertMenuQuantity = MenuItem.getDessertMenuQuantity(orders);
        Assertions.assertThat(dessertMenuQuantity).isEqualTo(2);
    }

    @DisplayName("주문에 디저트 없을 때 디저트 개수 구하기")
    @Test
    void getNotExistDessertMenuQuantity() {
        Orders orders = christmasService.createOrders("티본스테이크-1,양송이수프-1");
        int dessertMenuQuantity = MenuItem.getDessertMenuQuantity(orders);
        Assertions.assertThat(dessertMenuQuantity).isEqualTo(0);
    }

    @DisplayName("주문의 메인 메뉴 개수 구하기")
    @Test
    void getMainMenuQuantityTest() {
        Orders orders = christmasService.createOrders("티본스테이크-3,양송이수프-1");
        int mainMenuQuantity = MenuItem.getMainMenuQuantity(orders);
        Assertions.assertThat(mainMenuQuantity).isEqualTo(3);
    }

    @DisplayName("메인 메뉴 없는 주문의 메인 메뉴 개수 구하기")
    @Test
    void getNotExistMainMenuQuantityTest() {
        Orders orders = christmasService.createOrders("초코케이크-1,양송이수프-1");
        int mainMenuQuantity = MenuItem.getMainMenuQuantity(orders);
        Assertions.assertThat(mainMenuQuantity).isEqualTo(0);
    }
}