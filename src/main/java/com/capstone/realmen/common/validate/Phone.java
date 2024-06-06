package com.capstone.realmen.common.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.capstone.realmen.common.validate.constrains.PhoneValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Phone {
    String message() default "Số điện thoại không hợp lệ";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};
}
