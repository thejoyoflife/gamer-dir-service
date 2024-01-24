package com.example.gamer.directory.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidGamerSearchRequestConstraintValidator.class)
@Documented
public @interface ValidGamerSearchRequest {
	String message() default "Any of Interest Level, Name or Geography is mandatory";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
