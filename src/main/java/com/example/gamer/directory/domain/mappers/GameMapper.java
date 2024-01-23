package com.example.gamer.directory.domain.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.dtos.GameDTO;
import com.example.gamer.directory.domain.dtos.GamerDTO;
import com.example.gamer.directory.domain.dtos.GamerEnrollmentDTO;
import com.example.gamer.directory.domain.entities.Game;
import com.example.gamer.directory.domain.entities.Gamer;
import com.example.gamer.directory.exceptions.NotFoundException;
import com.example.gamer.directory.repositories.GameRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GameMapper {
	
	@Autowired
	private GameRepository gameRepository;
	
	public abstract GameDTO toGameDTO(Game game);
	public abstract List<GameDTO> toGameDTOAll(List<Game> games);
	
	@Mapping(source = "interests", target = "interests", qualifiedByName = "mapGameIdToGame")
	public abstract Gamer toGamerFromEnrollmentDTO(GamerEnrollmentDTO enrollment);
	public abstract GamerDTO toGamerDTO(Gamer gamer);
	
	@Named("mapGameIdToGame")
	public Map<Game, InterestLevel> mapGameIdToGame(Map<Integer, InterestLevel> interests) {
		if (interests == null) {
			return null;
		}
		
		Map<Game, InterestLevel> result = new HashMap<>();
		
		for (Map.Entry<Integer, InterestLevel> entry : interests.entrySet()) {
			Integer gameId = entry.getKey();
			Game game = gameRepository.findById(gameId)
				.orElseThrow(
						() -> new NotFoundException("Game ID [%d] not found".formatted(gameId)));
			result.put(game, entry.getValue());
		}
		
		return result;
	}
}
