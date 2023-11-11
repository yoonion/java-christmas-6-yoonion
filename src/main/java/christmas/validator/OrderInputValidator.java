package christmas.validator;

public class OrderInputValidator {

    private final static String DEFAULT_ERROR_MESSAGE = ValidatorConstants.DEFAULT_ERROR_MESSAGE;
    private final static String INVALID_ORDER_MESSAGE = DEFAULT_ERROR_MESSAGE + "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private final static int ORDER_MINIMUM_QUANTITY = 1;
    private final static int ORDER_MAXIMUM_QUANTITY = 20;

    public void checkOrderFormat(String inputOrderMenuAndQuantity) {
        checkContainHyphen(inputOrderMenuAndQuantity);
        if (isContainComma(inputOrderMenuAndQuantity)) {
            String[] orderMenuAndQuantities = inputOrderMenuAndQuantity.split(",");
            int totalOrderQuantity = 0;
            for (String orderMenuAndQuantity : orderMenuAndQuantities) {
                String orderMenu = orderMenuAndQuantity.split("-")[0];
                String orderQuantity = orderMenuAndQuantity.split("-")[1];
                checkOrderQuantityRange(orderQuantity);
                totalOrderQuantity += Integer.parseInt(orderQuantity);
            }
            checkTotalOrderQuantity(totalOrderQuantity);
        }
    }

    private void checkContainHyphen(String inputOrderMenuAndQuantity) {
        if (!inputOrderMenuAndQuantity.contains("-")) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private boolean isContainComma(String inputOrderMenuAndQuantity) {
        if (inputOrderMenuAndQuantity.contains(",")) {
            return true;
        }
        return false;
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
        if (totalOrderQuantity > ORDER_MAXIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }
}
