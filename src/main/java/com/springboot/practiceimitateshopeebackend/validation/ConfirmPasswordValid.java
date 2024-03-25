package com.springboot.practiceimitateshopeebackend.validation;

import com.springboot.practiceimitateshopeebackend.validation.Impl.ConfirmPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface ConfirmPasswordValid {
    String message() default "{password.mismatch}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
