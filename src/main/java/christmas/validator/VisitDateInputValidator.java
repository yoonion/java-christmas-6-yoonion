package christmas.validator;

public class VisitDateInputValidator {

    private final static String DEFAULT_ERROR_MESSAGE = ValidatorConstants.DEFAULT_ERROR_MESSAGE;

    private final static String INVALID_DATE_MESSAGE = DEFAULT_ERROR_MESSAGE + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void checkStringIsNumber(String inputVisitDate) {
        try {
            Integer.parseInt(inputVisitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public void checkVisitDateRange(String inputVisitDate) {
        int visitDate = Integer.parseInt(inputVisitDate);
        if (!(visitDate >= 1 && visitDate <= 31)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }
}
