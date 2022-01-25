package com.fon.hotel.validator.annotation;

import com.fon.hotel.validator.PresentOrFutureValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresentOrFutureValidator.class)
@Documented
public @interface PresentOrFuture {
    String message() default "Datum ne moze biti datum u proslosti";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
