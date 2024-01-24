package com.example.gamer.directory.validators;

import org.apache.commons.lang3.StringUtils;

import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGamerSearchRequestConstraintValidator 
			implements ConstraintValidator<ValidGamerSearchRequest, GamerSearchRequestDTO> {
	
	@Override
	public boolean isValid(GamerSearchRequestDTO searchRequest, ConstraintValidatorContext context) {
		return searchRequest.level() != null
				|| StringUtils.isNotBlank(searchRequest.gameName())
				|| searchRequest.geography() != null;
	}
}
