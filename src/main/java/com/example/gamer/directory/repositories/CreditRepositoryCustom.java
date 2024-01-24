package com.example.gamer.directory.repositories;

import java.util.List;

import com.example.gamer.directory.domain.dtos.MaxCreditGameDTO;

public interface CreditRepositoryCustom {
	public List<MaxCreditGameDTO> gamesWithMaxCredit();
}
