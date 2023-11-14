package christmas.model.order;

import christmas.model.menu.MenuItem;
import christmas.validator.ValidatorConstants;

import java.util.Collections;
import java.util.Map;

public class Orders {

    private static final int ORDER_MINIMUM_QUANTITY = 1;
    private static final int ORDER_MAXIMUM_QUANTITY = 20;
    private static final String DEFAULT_ERROR_MESSAGE = ValidatorConstants.DEFAULT_ERROR_MESSAGE;
    private static final String INVALID_ORDER_MESSAGE = DEFAULT_ERROR_MESSAGE + "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private final Map<String, Integer> orders;

    public Orders(Map<String, Integer> orders) {
        validateExistMenu(orders);
        validateMenuQuantityRange(orders);
        validateOrderOnlyBeverage(orders);
        validateTotalOrderQuantity(orders);
        this.orders = orders;
    }

    public Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }

    private void validateExistMenu(Map<String, Integer> orders) {
        for (String menu : orders.keySet()) {
            if (!MenuItem.hasItemName(menu)) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        }
    }

    private void validateMenuQuantityRange(Map<String, Integer> orders) {
        for (Integer quantity : orders.values()) {
            if (!(quantity >= ORDER_MINIMUM_QUANTITY && quantity <= ORDER_MAXIMUM_QUANTITY)) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        }
    }

    private void validateOrderOnlyBeverage(Map<String, Integer> orders) {
        boolean isMenuOnlyBeverage = true;
        for (String menu : orders.keySet()) {
            if(isMenuOnlyBeverage){
                isMenuOnlyBeverage = MenuItem.isMenuBeverage(menu);
            }
        }
        if (isMenuOnlyBeverage) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void validateTotalOrderQuantity(Map<String, Integer> orders) {
        int totalOrderQuantity = 0;
        for (Integer quantity : orders.values()) {
            totalOrderQuantity += quantity;
        }
        if (totalOrderQuantity > ORDER_MAXIMUM_QUANTITY || totalOrderQuantity < ORDER_MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }
}
