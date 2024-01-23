package com.example.gamer.directory.domain.dtos;

import java.util.Map;

import com.example.gamer.directory.domain.Gender;
import com.example.gamer.directory.domain.Geography;
import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.validators.ValidGameId;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GamerEnrollmentDTO(
		@Schema(description = "Name of the Gamer",
				example = "XXX YYYY")
		@NotBlank
		String name, 
		
		@Schema(description = "Nickname of the Gamer", 
				example = "XXX")
		String nickname,
		
		@Schema(description = "Gender of the Gamer", example = "male")
		Gender gender, 
		
		@Schema(description = "Region where the Gamer lives in", example = "asia")
		Geography geography, 
		
		@Schema(description = "Interests in the games with their levels", 
				example = "{\"GameID-1\": \"InterestLevel-1\", \"GameID-2\": \"InterestLevel-2\"}")
		@Size(min = 1, message = "Interest set is empty")
		@ValidGameId
		Map<Integer, InterestLevel> interests) {}
