package com.example.gamer.directory.domain.dtos;

import com.example.gamer.directory.domain.Geography;
import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.validators.ValidGamerSearchRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@ValidGamerSearchRequest
@Builder
public record GamerSearchRequestDTO(
			@Schema(description = "Interest level that gamers have shown their interest for various games", example = "noob")
			InterestLevel level,
			@Schema(description = "Name of the Game that gamers have shown their interest for", example = "fortnite")
			String gameName,
			@Schema(description = "Region where the Gamer lives in", example = "asia")
			Geography geography
		) {
}
