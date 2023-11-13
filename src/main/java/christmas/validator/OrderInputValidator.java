package christmas.validator;

import java.util.Arrays;
import java.util.List;

public class OrderInputValidator {

    public void checkOrderFormat(String inputOrderMenuAndQuantity) {
        List<String> orders = convertOrderStringToList(inputOrderMenuAndQuantity);
        checkContainHyphen(inputOrderMenuAndQuantity);
        checkOrderDuplicateMenu(orders);
        checkMenuQuantityIsNumber(orders);
    }

    private void checkContainHyphen(String inputOrderMenuAndQuantity) {
        if (!inputOrderMenuAndQuantity.contains("-")) {
            throw new IllegalArgumentException(ValidatorConstants.INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderDuplicateMenu(List<String> orders) {
        for (String order : orders) {
            String menu = order.split("-")[0];
            if (getMenuDuplicateCount(orders, menu) > 1) {
                throw new IllegalArgumentException(ValidatorConstants.INVALID_ORDER_MESSAGE);
            }
        }
    }

    private int getMenuDuplicateCount(List<String> orders, String targetMenu) {
        int duplicateCount = 0;
        for (String order : orders) {
            if (order.contains(targetMenu)) {
                duplicateCount++;
            }
        }
        return duplicateCount;
    }

    private void checkMenuQuantityIsNumber(List<String> orders) {
        for (String order : orders) {
            String menuQuantity = order.split("-")[1];
            try {
                Integer.parseInt(menuQuantity);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ValidatorConstants.INVALID_ORDER_MESSAGE);
            }
        }
    }

    private static List<String> convertOrderStringToList(String inputOrderMenuAndQuantity) {
        return Arrays.asList(inputOrderMenuAndQuantity.split(","));
    }
}
