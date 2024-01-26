package com.example.gamer.directory.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import com.example.gamer.directory.GamerDirectoryBaseIntegrationTest;
import com.example.gamer.directory.domain.Geography;
import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.dtos.GamerSearchRequestDTO;
import com.example.gamer.directory.domain.entities.Gamer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Sql(value = "/insert_gamer_data.sql", executionPhase = BEFORE_TEST_CLASS)
@Sql(value = "/delete_gamer_data.sql", executionPhase = AFTER_TEST_CLASS)
public class GamerRepositoryTest extends GamerDirectoryBaseIntegrationTest {
	@Autowired
	private GamerRepository gamerRepo;
	
	@Test
	@DisplayName("Should Return Total 4 Gamers")
	void shouldReturnCorrectNumberOfGamers() {		
		Long gamersCount = gamerRepo.count();		
		assertEquals(4, gamersCount);
	}
	
	@Test
	@DisplayName("Should Return 2 Gamers in Europe Geography")
	void shouldReturn2GamersInEurope() {
		var searchReq = GamerSearchRequestDTO.builder()
								.geography(Geography.EUROPE)
								.build();
		Page<Gamer> gamers = gamerRepo.searchGamers(searchReq, PageRequest.of(0, 10));
		assertEquals(2, gamers.getNumberOfElements());
	}
	
	@Test
	@DisplayName("Should Return 1 Gamer in Europe Geography With NOOB Interest Level")
	void shouldReturn1GamerInEuropeWithNOOBInterestLevel() {
		var searchReq = GamerSearchRequestDTO.builder()
								.level(InterestLevel.NOOB)
								.geography(Geography.EUROPE)
								.build();
		Page<Gamer> gamers = gamerRepo.searchGamers(searchReq, PageRequest.of(0, 10));
		assertEquals(1, gamers.getNumberOfElements());
		assertEquals("shimul kumar saha", gamers.getContent().get(0).getName());
	}
}
