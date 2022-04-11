package com.cygnet.userservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = CheckMailIDValidator.class)
public @interface CheckMailID
{
    public String message () default "There is no user with this mail!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
