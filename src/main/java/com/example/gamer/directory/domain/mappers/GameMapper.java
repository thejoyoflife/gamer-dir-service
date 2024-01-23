package com.example.gamer.directory.domain.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.gamer.directory.domain.dtos.GameDTO;
import com.example.gamer.directory.domain.entities.Game;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GameMapper {
	
	public abstract GameDTO toGameDTO(Game game);
	public abstract List<GameDTO> toGameDTOAll(List<Game> games);
	
}
