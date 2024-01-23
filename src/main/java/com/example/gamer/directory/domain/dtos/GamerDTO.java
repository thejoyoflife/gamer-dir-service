package com.example.gamer.directory.domain.dtos;

import com.example.gamer.directory.domain.Gender;
import com.example.gamer.directory.domain.Geography;

import io.swagger.v3.oas.annotations.media.Schema;

public record GamerDTO(
		@Schema(description = "ID of the Gamer", example = "1")
		Long id, 
		@Schema(description = "Name of the Gamer", example = "XXXX YYY")
		String name, 
		@Schema(description = "Nickname of the Gamer", example = "ZZZ")
		String nickname, 
		@Schema(description = "Gender of the Gamer", example = "male")
		Gender gender, 
		@Schema(description = "Region where the Gamer lives in", example = "asia")
		Geography geography) {}
