package com.example.task_project.controller.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CityNameValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CityNameConstraint {

    String message() default "Invalid city name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
