package com.example.gamer.directory.validators;

import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.entities.Game;
import com.example.gamer.directory.repositories.GameRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidGameIdConstraintValidator 
				implements ConstraintValidator<ValidGameId, Map<Integer, InterestLevel>> {
	
	private final GameRepository gameRepository;

	@Override
	public boolean isValid(Map<Integer, InterestLevel> interests, ConstraintValidatorContext context) {
		if (interests == null) { 
			return true;
		}
		
		var givenGameIds = interests.keySet();
		var existingGameIds = gameRepository.findAllById(givenGameIds)
								.stream().map(Game::getId)
								.toList();
		var invalidGameIds = new HashSet<>(givenGameIds);
		invalidGameIds.removeAll(existingGameIds);
		return invalidGameIds.size() == 0; 
	}
	
}
