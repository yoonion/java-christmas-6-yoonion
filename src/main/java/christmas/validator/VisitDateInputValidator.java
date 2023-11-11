package christmas.validator;

public class VisitDateInputValidator {

    private static final String DEFAULT_ERROR_MESSAGE = ValidatorConstants.DEFAULT_ERROR_MESSAGE;
    private static final String INVALID_DATE_MESSAGE = DEFAULT_ERROR_MESSAGE + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MINIMUM_VISIT_DATE = 1;
    private static final int MAXIMUM_VISIT_DATE = 31;

    public void checkStringIsNumber(String inputVisitDate) {
        try {
            Integer.parseInt(inputVisitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }

    public void checkVisitDateRange(String inputVisitDate) {
        int visitDate = Integer.parseInt(inputVisitDate);
        if (!(visitDate >= MINIMUM_VISIT_DATE && visitDate <= MAXIMUM_VISIT_DATE)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
    }
}
