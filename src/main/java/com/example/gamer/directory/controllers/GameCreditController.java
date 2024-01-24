package com.example.gamer.directory.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamer.directory.domain.dtos.CreditDTO;
import com.example.gamer.directory.services.CreditService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
@Tag(name = "Credit API", description = "APIs to award credits and search for top scored games")
public class GameCreditController {
	
	private final CreditService creditService;
	
	@Operation(summary = "Award credit to a gamer for a specific game", 
			description = "This API allows a credit, represented as an integer number, to be awarded for a specific game")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successfully awarded credit"),
		@ApiResponse(responseCode = "400", description = "Invalid Input",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))		
	})
	@PostMapping(name = "awardCredit", value = "/award-credit",
			consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE)
	public ResponseEntity<String> awardCredit(@Valid @RequestBody CreditDTO credit) {
		creditService.awardCredit(credit);
		return ResponseEntity.ok("Credit is awarded successfully");
	}
}
