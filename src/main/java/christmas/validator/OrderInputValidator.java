package christmas.validator;

import java.util.Arrays;
import java.util.List;

public class OrderInputValidator {

    public void checkOrderFormat(String inputOrderMenuAndQuantity) {
        checkContainHyphen(inputOrderMenuAndQuantity);
        List<String> orders = convertOrderStringToList(inputOrderMenuAndQuantity);
        checkOrderDuplicateMenu(orders);
        checkMenuQuantityIsNumber(orders);
    }

    private void checkContainHyphen(String inputOrderMenuAndQuantity) {
        if (!inputOrderMenuAndQuantity.contains("-")) {
            throw new IllegalArgumentException(ValidatorConstants.INVALID_ORDER_MESSAGE);
        }
    }

    private void checkOrderDuplicateMenu(List<String> orders) {
        if(orders.size() != orders.stream().distinct().count()){
            throw new IllegalArgumentException(ValidatorConstants.INVALID_ORDER_MESSAGE);
        }
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
