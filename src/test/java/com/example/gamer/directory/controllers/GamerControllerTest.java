package com.example.gamer.directory.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.gamer.directory.domain.Gender;
import com.example.gamer.directory.domain.Geography;
import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.dtos.GamerEnrollmentDTO;
import com.example.gamer.directory.domain.entities.Game;
import com.example.gamer.directory.repositories.GameRepository;
import com.example.gamer.directory.services.GamerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = GamerController.class)
public class GamerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private GamerService gamerService;
	
	@MockBean
	private GameRepository gameRepo;

	
	@Test
	@DisplayName("Should Enroll A Gamer Successfully In An Existing Game")
	void shouldEnrollSuccessfully() throws Exception {
		Game game = new Game();
		game.setId(1); game.setName("fortnite");
		var enrollment = GamerEnrollmentDTO.builder()
							.name("shimul kumar saha")
							.nickname("shimul")
							.gender(Gender.MALE)
							.geography(Geography.EUROPE)
							.interests(Map.of(1, InterestLevel.NOOB))
							.build();
		
		when(gameRepo.findAllById(Set.of(1))).thenReturn(List.of(game));
		when(gamerService.enroll(enrollment)).thenReturn(100l);

		String enrollmentJson = objectMapper.writeValueAsString(enrollment);
		
		mockMvc.perform(
					post("/api/v1/gamers/enroll")
					.contentType(MediaType.APPLICATION_JSON)
					.content(enrollmentJson))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Gamer enrolled successfully with id: 100"));
				
		/*
		var enrollInputJson = """
				{
				  "name": "shimul saha", 
				  "nickname": "shimul", 
				  "gender": "%s", 
				  "geography": "%s",
				  "interests": {
				  	"1": "%s"
				  }
				}
				""".formatted(Gender.MALE, Geography.EUROPE, InterestLevel.NOOB);
		
		webTestClient.post()
			.uri("/api/v1/gamers/enroll")
			.contentType(APPLICATION_JSON)
			.bodyValue(enrollInputJson)
			.exchange()
			.expectStatus().isOk()
			.expectHeader()
				.contentTypeCompatibleWith(TEXT_PLAIN)
			.expectBody(String.class)
			.value(resp -> assertThat(resp).contains("Gamer enrolled successfully"));
			*/
	}
}
