package counterparties.service.constraint;

import counterparties.service.validator.TinValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TinValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TinConstraint {
    String message() default "Некорректный ИНН";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
