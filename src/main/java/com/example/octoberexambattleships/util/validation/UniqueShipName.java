package com.example.octoberexambattleships.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueShipNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueShipName {
    String message() default "Ship name already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
