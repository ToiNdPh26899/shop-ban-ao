package com.toindph26899.validation;

import com.toindph26899.validation.constraint.NotEmptyListConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyListConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyList {

    String message() default "Chọn 1 sản phẩm trước khi thanh toán";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
