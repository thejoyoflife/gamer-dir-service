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
@Constraint(validatedBy = ValidCreditRequestConstraintValidator.class)
@Documented
public @interface ValidCreditRequest {
	String message() default "Game and/or Gamer ID is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
