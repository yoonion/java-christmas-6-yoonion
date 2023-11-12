package christmas.validator;

public class VisitDateInputValidator {

    private static final int MINIMUM_VISIT_DATE = 1;
    private static final int MAXIMUM_VISIT_DATE = 31;

    public void checkStringIsNumber(String inputVisitDate) {
        try {
            Integer.parseInt(inputVisitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidatorConstants.INVALID_DATE_MESSAGE);
        }
    }

    public void checkVisitDateRange(String inputVisitDate) {
        int visitDate = Integer.parseInt(inputVisitDate);
        if (!(visitDate >= MINIMUM_VISIT_DATE && visitDate <= MAXIMUM_VISIT_DATE)) {
            throw new IllegalArgumentException(ValidatorConstants.INVALID_DATE_MESSAGE);
        }
    }
}
