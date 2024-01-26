package com.example.gamer.directory.controllers;

import static com.example.gamer.directory.common.TestConstants.INVALID_GAME_ID;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.gamer.directory.GamerDirectoryBaseIntegrationTest;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(statements = "insert into game(id, name) values (1, 'fortnite'), (2, 'amongus')", executionPhase = BEFORE_TEST_CLASS)
@Sql(statements = "delete from game where id in (1, 2)", executionPhase = AFTER_TEST_CLASS)
public class GameControllerTest extends GamerDirectoryBaseIntegrationTest {
	
	@Autowired
	private WebTestClient webClient;
	
	@Test
	@DisplayName("Should Return All Games Successfully")
	void shouldReturnAllGamesSuccessfully() {
		var expectedJson = """
				[{"id": 1, "name": "fortnite"}, {"id": 2, "name": "amongus"}]
				""";
		webClient.get()
			.uri("/api/v1/games")
			.exchange()
			.expectStatus().isOk()
			.expectBody().json(expectedJson);
	}
	
	@Test
	@DisplayName("Should Return A Game By Its ID Successfully")
	void shouldReturnAGameByIDSuccessfully() {
		var expectedJson = """
				{"id": 1, "name": "fortnite"}
				""";
		webClient.get()
			.uri("/api/v1/games/{id}", 1)
			.exchange()
			.expectStatus().isOk()
			.expectBody().json(expectedJson);
	}
	
	@Test
	@DisplayName("Should Return 404 When A Invalid Game ID Is Submitted")
	void shouldReturn404OnInvalidGameId() {
		webClient.get()
			.uri("/api/v1/games/{id}", INVALID_GAME_ID)
			.exchange()
			.expectStatus().isNotFound()
			.expectBody()
				.jsonPath("$.detail")
				.isEqualTo("Game not found for id: %d".formatted(INVALID_GAME_ID));
	}
 }
