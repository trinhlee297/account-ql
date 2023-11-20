package com.account.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = AccountIdExistsValidator.class
)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface AccountIdExists {
    String message() default "{The account id doesn't exists.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

