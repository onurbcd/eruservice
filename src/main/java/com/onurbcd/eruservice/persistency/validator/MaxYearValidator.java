package com.onurbcd.eruservice.persistency.validator;

import com.onurbcd.eruservice.persistency.constraint.MaxYear;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class MaxYearValidator implements ConstraintValidator<MaxYear, Short> {

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var currentYear = LocalDateTime.now().getYear();
        var result = value <= currentYear;

        if (!result) {
            var hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
            hibernateContext.addMessageParameter("value", currentYear);
            hibernateContext.disableDefaultConstraintViolation();
            hibernateContext.buildConstraintViolationWithTemplate("{javax.validation.constraints.Max.message}")
                    .addConstraintViolation();
        }

        return result;
    }
}
