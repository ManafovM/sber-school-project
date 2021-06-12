package counterparties.service.constraint;

import counterparties.service.validator.AccountNumberAndBicValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AccountNumberAndBicValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNumberAndBicConstraint {
    String message() default "Некорректный номер счета и/или БИК.";

    String accountNumber();

    String bic();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
