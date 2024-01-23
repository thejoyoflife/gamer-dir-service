package com.example.gamer.directory.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamer.directory.domain.dtos.GameDTO;
import com.example.gamer.directory.services.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
@Tag(name = "Game API", description = "APIs to manage different games")
public class GameController {

	private final GameService gameService;
	
	@Operation(summary = "Returns all games in the system", 
			description = "This API can be used to see the available games in the system")
	@GetMapping(name = "getAllGames", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GameDTO>> getAllGames() {
		var allGames = gameService.allGames();
		return ResponseEntity.ok(allGames);
	}
	
	@Operation(summary = "Returns a specific game by its identifier", 
			description = "This API can be used to find a specific game by its id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successfully retrieved",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GameDTO.class))),
		@ApiResponse(responseCode = "404", description = "Not found - The game was not found",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
	})
	@GetMapping(name = "getGameById", value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<GameDTO> getGameById(@PathVariable(value = "id", required = true) 
												@Parameter(description = "Game ID", example = "1")
												@NonNull Integer id) {
		var game = gameService.findGameById(id);
		return ResponseEntity.ok(game);
	}
}
