package com.example.gamer.directory.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.data.domain.Page;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamer.directory.domain.dtos.GamerDTO;
import com.example.gamer.directory.domain.dtos.GamerEnrollmentDTO;
import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;
import com.example.gamer.directory.services.GamerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/gamers")
@RequiredArgsConstructor
@Tag(name = "Gamer API", description = "APIs to enroll and search gamers")
public class GamerController {
	
	private final GamerService gamerService;
	
	@Operation(summary = "Enroll a Gamer into different games", 
		description = "This API can be used to enroll a gamer to different kinds of games along with their interest levels")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successfully enrolled"),
		@ApiResponse(responseCode = "400", description = "Invalid Input",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))		
	})
	@PostMapping(name = "enroll", value = "/enroll", 
		consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE)
	public ResponseEntity<String> enroll(@Valid @RequestBody GamerEnrollmentDTO gamerRequest) {
		Long gamerId = gamerService.enroll(gamerRequest);
		return ResponseEntity.ok("Gamer enrolled successfully with id: %d".formatted(gamerId));
	}
	
	@Operation(summary = "Returns a specific Gamer by its identifier", 
			description = "This API can be used to find a specific Gamer by its id")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Successfully retrieved",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GamerDTO.class))),
		@ApiResponse(responseCode = "404", description = "Not found - The Gamer was not found",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
	})
	@GetMapping(name = "getGamerById", value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<GamerDTO> getGamerById(@PathVariable(value = "id", required = true) 
												@Parameter(description = "Gamer ID", example = "1")
												@NonNull Long id) {
		var gamer = gamerService.findGamerById(id);
		return ResponseEntity.ok(gamer);
	}
	
	@Operation(summary = "Pagainaed Search for Gamers by different criteria", 
			description = "This API can be used to find Gamers by their geography, specific game name and the interest level they showed for them")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Search was successful",
			content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Page.class))),
		@ApiResponse(responseCode = "400", description = "Invalid Input",
		content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))	
	})
	@PostMapping(name = "searchGamers", value = "/search",
			consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<GamerDTO>> searchGamers(@Valid @RequestBody GamerSearchRequestDTO searchRequest,								
														@Parameter(description = "Page offset/number", example = "0")
														@RequestParam(name = "offset", defaultValue = "0") @Min(0) int pageOffset,
														@Parameter(description = "Number of gamer records per page", example = "10")
														@RequestParam(name = "size", defaultValue = "10") @Max(100) int pageSize
														) {
		var gamers = gamerService.findGamers(searchRequest, pageOffset, pageSize);
		return ResponseEntity.ok(gamers);
	}
	
}
