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
        validate(orders);
        this.orders = orders;
    }

    public Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }

    private void validate(Map<String, Integer> orders) {
        int totalOrderQuantity = 0;
        boolean isMenuOnlyBeverage = true;
        for (Map.Entry<String, Integer> order : orders.entrySet()) {
            String orderMenu = order.getKey();
            Integer orderQuantity = order.getValue();
            checkOrderMenuExist(orderMenu);
            checkOrderQuantityRange(orderQuantity);

            if(isMenuOnlyBeverage){
                isMenuOnlyBeverage = MenuItem.isMenuBeverage(orderMenu);
            }
            totalOrderQuantity += orderQuantity;
        }
        checkOrderOnlyBeverage(isMenuOnlyBeverage);
        checkTotalOrderQuantity(totalOrderQuantity);
    }

    private void checkOrderOnlyBeverage(boolean isMenuOnlyBeverage) {
        if (isMenuOnlyBeverage) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderMenuExist(String orderMenu) {
        if (!MenuItem.hasItemName(orderMenu)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderQuantityRange(Integer orderQuantity) {
        try {
            if (!(orderQuantity >= ORDER_MINIMUM_QUANTITY && orderQuantity <= ORDER_MAXIMUM_QUANTITY)) {
                throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkTotalOrderQuantity(int totalOrderQuantity) {
        if (totalOrderQuantity > ORDER_MAXIMUM_QUANTITY || totalOrderQuantity < ORDER_MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }
}
