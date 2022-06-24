package com.example.springbootbackend.config.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

// Unique Email Configuration for validation..

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "Unique E-mail ! ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
