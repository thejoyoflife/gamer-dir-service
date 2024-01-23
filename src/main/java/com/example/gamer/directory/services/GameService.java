package com.example.gamer.directory.services;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gamer.directory.domain.dtos.GameDTO;
import com.example.gamer.directory.domain.mappers.GameMapper;
import com.example.gamer.directory.exceptions.NotFoundException;
import com.example.gamer.directory.repositories.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {
	private final GameMapper gameMapper;
	private final GameRepository gameRepository;
	
	public List<GameDTO> allGames() {
		var allGames = gameRepository.findAll();
		return gameMapper.toGameDTOAll(allGames);
	}
	
	public GameDTO findGameById(@NonNull Integer id) {
		var game = gameRepository.findById(id)
							.orElseThrow(() -> 
								new NotFoundException("Game not found for id: %d".formatted(id)));
		return gameMapper.toGameDTO(game);
	}
}
