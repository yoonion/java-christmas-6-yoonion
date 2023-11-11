package christmas.validator;

import christmas.model.MenuItem;

import java.util.Arrays;
import java.util.List;

public class OrderInputValidator {

    private static final String DEFAULT_ERROR_MESSAGE = ValidatorConstants.DEFAULT_ERROR_MESSAGE;
    private static final String INVALID_ORDER_MESSAGE = DEFAULT_ERROR_MESSAGE + "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_DELIMITER = ",";
    private static final String MENU_AND_QUANTITY_DELIMITER = "-";

    private static final int ORDER_MINIMUM_QUANTITY = 1;
    private static final int ORDER_MAXIMUM_QUANTITY = 20;

    public void checkOrderFormat(String inputOrderMenuAndQuantity) {
        checkContainHyphen(inputOrderMenuAndQuantity);

        int totalOrderQuantity = 0;
        String[] orderMenuAndQuantities = inputOrderMenuAndQuantity.split(ORDER_DELIMITER);
        for (String orderMenuAndQuantity : orderMenuAndQuantities) {
            String orderMenu = orderMenuAndQuantity.split(MENU_AND_QUANTITY_DELIMITER)[0];
            checkOrderMenuExist(orderMenu);
            String orderQuantity = orderMenuAndQuantity.split(MENU_AND_QUANTITY_DELIMITER)[1];
            checkOrderQuantityRange(orderQuantity);
            totalOrderQuantity += Integer.parseInt(orderQuantity);
        }
        checkTotalOrderQuantity(totalOrderQuantity);
    }

    public void checkOrderDuplicateMenu(String inputOrderMenuAndQuantity) {
        List<String> numList = Arrays.asList(inputOrderMenuAndQuantity.split(ORDER_DELIMITER));
        if(numList.size() != numList.stream().distinct().count()){
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderMenuExist(String orderMenu) {
        if (!MenuItem.hasItemName(orderMenu)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkContainHyphen(String inputOrderMenuAndQuantity) {
        if (!inputOrderMenuAndQuantity.contains(MENU_AND_QUANTITY_DELIMITER)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderQuantityRange(String orderQuantity) {
        try {
            int quantity = Integer.parseInt(orderQuantity);
            if (!(quantity >= ORDER_MINIMUM_QUANTITY && quantity <= ORDER_MAXIMUM_QUANTITY)) {
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
