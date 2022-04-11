package com.cygnet.userservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername
{
    public String message () default "There is already user with this name!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
