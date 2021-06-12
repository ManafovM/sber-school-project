package counterparties.service.validator;

import counterparties.service.constraint.AccountNumberAndBicConstraint;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountNumberAndBicValidator implements ConstraintValidator<AccountNumberAndBicConstraint, Object> {
    private String accountNumberField;
    private String bicField;
    private final static List<Integer> ACCOUNT_VAL_WEIGHTS = List.of(
            7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1);

    @Override
    public void initialize(AccountNumberAndBicConstraint constraintAnnotation) {
        this.accountNumberField = constraintAnnotation.accountNumber();
        this.bicField = constraintAnnotation.bic();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String accountNumberValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(this.accountNumberField);
        String bicValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(this.bicField);

        List<Integer> accountNumber = ValidatorUtils.stringToIntegers(
                Objects.requireNonNull(accountNumberValue));
        List<Integer> bic = ValidatorUtils.stringToIntegers(
                Objects.requireNonNull(bicValue));

        List<Integer> bicAndAccountNumber;
        if (bic.get(6) == 0 && bic.get(7) == 0) {
            bicAndAccountNumber = Stream
                    .concat(bic.stream().skip(4).limit(2), accountNumber.stream())
                    .collect(Collectors.toList());
            bicAndAccountNumber.add(0, 0);
        } else {
            bicAndAccountNumber = Stream
                    .concat(bic.stream().skip(6), accountNumber.stream())
                    .collect(Collectors.toList());
        }

        int controlSum = ValidatorUtils.getControlSum(bicAndAccountNumber, ACCOUNT_VAL_WEIGHTS);
        int controlNumber = controlSum % 10;
        return controlNumber == 0;
    }
}
