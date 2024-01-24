package com.example.gamer.directory.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gamer.directory.domain.dtos.CreditDTO;
import com.example.gamer.directory.domain.dtos.MaxCreditGameDTO;
import com.example.gamer.directory.domain.entities.Credit;
import com.example.gamer.directory.exceptions.CreditAlreadyAwardedException;
import com.example.gamer.directory.repositories.CreditRepository;
import com.example.gamer.directory.repositories.GameRepository;
import com.example.gamer.directory.repositories.GamerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditService {

	private final CreditRepository creditRepository;
	private final GamerRepository gamerRepository;
	private final GameRepository gameRepository;
	
	@Transactional
	public void awardCredit(CreditDTO credit) {
		var game = gameRepository.getReferenceById(credit.gameId());
		var gamer = gamerRepository.getReferenceById(credit.gamerId());
		
		var creditAlreadyGiven = creditRepository.isCreditAlreadyAwarded(gamer.getId(), game.getId());
		if (creditAlreadyGiven) {
			throw new CreditAlreadyAwardedException(
					"Credit is already awarded to Gamer: %d for the Game: %d"
						.formatted(gamer.getId(), game.getId()));
		}		
		
		var newCredit = new Credit();
		newCredit.setGame(game);
		newCredit.setGamer(gamer);
		newCredit.setAmount(credit.amount());
		
		creditRepository.save(newCredit);
	}
	
	public List<MaxCreditGameDTO> maxCreditGames() {
		return creditRepository.gamesWithMaxCredit();
	}
}
