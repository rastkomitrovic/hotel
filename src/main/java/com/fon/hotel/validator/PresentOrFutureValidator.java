package com.fon.hotel.validator;

import com.fon.hotel.validator.annotation.PresentOrFuture;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PresentOrFutureValidator implements ConstraintValidator<PresentOrFuture, Date> {
    @Override
    public void initialize(PresentOrFuture constraintAnnotation) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null)
            return false;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateNow = LocalDate.now();
        return !localDate.isBefore(localDateNow);
    }
}
