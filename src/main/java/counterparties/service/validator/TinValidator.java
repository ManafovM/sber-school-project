package counterparties.service.validator;

import counterparties.service.CounterpartyService;
import counterparties.service.constraint.TinConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.IntStream;

public class TinValidator implements ConstraintValidator<TinConstraint, String> {
    private final static List<Integer> TIN_VAL_WEIGHTS = List.of(2, 4, 10, 3, 5, 9, 4, 6, 8, 0);

    @Override
    public boolean isValid(String tinField, ConstraintValidatorContext context) {
        List<Integer> tinNumbers = CounterpartyService.stringToIntegers(tinField);
        int controlSum = IntStream.range(0, TIN_VAL_WEIGHTS.size())
                .map(i -> tinNumbers.get(i) * TIN_VAL_WEIGHTS.get(i))
                .sum();
        int controlNumber = controlSum % 11;
        if (controlNumber > 9) {
            controlNumber %= 10;
        }
        return tinNumbers.get(tinNumbers.size() - 1) == controlNumber;
    }
}
