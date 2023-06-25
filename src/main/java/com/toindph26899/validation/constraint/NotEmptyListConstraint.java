package com.toindph26899.validation.constraint;

import com.toindph26899.response.SanPhamChiTietResponse;
import com.toindph26899.validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListConstraint implements ConstraintValidator<NotEmptyList, List<Long>> {
    @Override
    public boolean isValid(List<Long> value, ConstraintValidatorContext context) {

        return value != null && !value.isEmpty();
    }
}
