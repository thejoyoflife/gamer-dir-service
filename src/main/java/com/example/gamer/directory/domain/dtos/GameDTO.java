package com.example.gamer.directory.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record GameDTO(
		@Schema(description = "Game ID", example = "1")
		Integer id, 
		@Schema(description = "Name of the game", example = "fortnite")
		String name) {}
