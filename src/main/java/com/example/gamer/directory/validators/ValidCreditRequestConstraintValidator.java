package com.example.gamer.directory.validators;

import org.springframework.stereotype.Component;

import com.example.gamer.directory.domain.dtos.CreditDTO;
import com.example.gamer.directory.exceptions.NotFoundException;
import com.example.gamer.directory.services.GameService;
import com.example.gamer.directory.services.GamerService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidCreditRequestConstraintValidator 
							implements ConstraintValidator<ValidCreditRequest, CreditDTO> {
	
	private final GameService gameService;
	private final GamerService gamerService;
	
	@Override
	public boolean isValid(CreditDTO creditReq, ConstraintValidatorContext context) {
		try {
			gameService.findGameById(creditReq.gameId());
			gamerService.findGamerById(creditReq.gamerId());
		} catch (NotFoundException nfe) {
			log.debug(nfe.getBody().getDetail());
			return false;
		}
		return true;
	}
}
