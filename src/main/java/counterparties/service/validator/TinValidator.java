package counterparties.service.validator;

import counterparties.service.constraint.TinConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TinValidator implements ConstraintValidator<TinConstraint, String> {
    private final static List<Integer> TIN_VAL_WEIGHTS = List.of(2, 4, 10, 3, 5, 9, 4, 6, 8, 0);

    /**
     * Проверяет корректность ИНН контрагента. Вычисляется контрольная сумма с весовыми коэффициентами
     * {@code TIN_VAL_WEIGHTS}. Вычисляется контрольное число как остаток от деления контрольной суммы на 11.
     * Если контрольное число больше 9, то контрольное число вычисляется как остаток от деления контрольного
     * числа на 10. Если контрольное число равно последней цифре ИНН, то ИНН считается корректным.
     *
     * @param tinField строка ИНН
     * @return true, если ИНН корректный, false - в противном случае
     */
    @Override
    public boolean isValid(String tinField, ConstraintValidatorContext context) {
        List<Integer> tinNumbers = ValidatorUtils.stringToIntegers(tinField);
        int controlSum = ValidatorUtils.getControlSum(tinNumbers, TIN_VAL_WEIGHTS);
        int controlNumber = controlSum % 11;
        if (controlNumber > 9) {
            controlNumber %= 10;
        }
        return tinNumbers.get(tinNumbers.size() - 1) == controlNumber;
    }
}
