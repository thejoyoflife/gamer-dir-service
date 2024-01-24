package com.example.gamer.directory.domain.dtos;

import com.example.gamer.directory.validators.ValidCreditRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@ValidCreditRequest
public record CreditDTO(
		@Schema(description = "Gamer ID or Gamer Enrollment ID", example = "1")
		@NotNull
		Long gamerId, 
		
		@Schema(description = "Game ID", example = "1")
		@NotNull
		Integer gameId, 
		
		@Schema(description = "Credit to be awarded, represented as an integer form", example = "10")
		@Min(0)
		Integer amount) {
}
