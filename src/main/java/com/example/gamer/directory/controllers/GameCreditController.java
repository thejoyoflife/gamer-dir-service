package com.example.gamer.directory.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamer.directory.domain.dtos.CreditDTO;
import com.example.gamer.directory.domain.dtos.MaxCreditGameDTO;
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
			description = "This API allows a credit, represented as an integer number, to be awarded for a specific game."
					+ " Credit can only be awarded once to a gamer for a particular game." 		
					+ " A gamer may also receive credit for a game in which they have shown no interest as well.")
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
	
	@Operation(summary = "Returns games with top scorer/gamer based on their interest level", 
			description = "This API returns the games along with their gamers who were awarded maximum credits based"
							+ "	on their individual interest levels. Since a gamer can be awarded a credit for a game"
							+ " s/he had shown no interest for, the awarded credit will be considered under an 'UNKNOWN' level."
							+ " The games for which no gamer is awarded a credit will not be shown either.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successful"),
	})
	@GetMapping(name = "getMaxCreditGames", value = "/max-credits",	produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaxCreditGameDTO>> getMaxCreditGames() {
		var maxCreditGames = creditService.maxCreditGames();
		return ResponseEntity.ok(maxCreditGames);
	}
}
