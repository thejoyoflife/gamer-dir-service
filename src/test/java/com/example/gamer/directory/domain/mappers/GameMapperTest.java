package com.example.gamer.directory.domain.mappers;

import static com.example.gamer.directory.common.TestConstants.INVALID_GAME_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.gamer.directory.GamerDirectoryBaseIntegrationTest;
import com.example.gamer.directory.domain.InterestLevel;
import com.example.gamer.directory.domain.entities.Game;
import com.example.gamer.directory.exceptions.NotFoundException;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GameMapperTest extends GamerDirectoryBaseIntegrationTest {
	
	@Autowired
	private GameMapper gameMapper;
	
	@Test
	@DisplayName("When Interests Map Contains Valid Entries, Then The Conversion Should Work Fine")
	@Sql(statements = "insert into game(id, name) values (6, 'fortnite'), (7, 'valhalla')")
	@Sql(statements = "delete from game where id in (6, 7)", executionPhase = AFTER_TEST_METHOD)
	void testGameIdConverstionSuccessfully() {
		Game game1 = new Game();
		game1.setId(6); game1.setName("fortnite");
		Game game2 = new Game();
		game2.setId(7); game2.setName("valhalla");
		
		var interestMapInput = Map.of(6, InterestLevel.NOOB, 7, InterestLevel.PRO);
		var interestMapOutput = gameMapper.mapGameIdToGame(interestMapInput);
		
		assertThat(interestMapOutput).hasSize(2);
		assertThat(interestMapOutput).containsOnlyKeys(game1, game2);
	}
	
	@Test
	@DisplayName("When input interest map is null, output interest map is also null")
	void testWhenInputInterestMapIsNullOutputInterestMapIsNull() {
		Map<Integer, InterestLevel> inputMap = null;
		
		var outputMap = gameMapper.mapGameIdToGame(inputMap);
		
		assertNull(outputMap);
	}
	
	@Test
	@DisplayName("When non existent game id is provided, a NotFoundException should be thrown")
	void testInvalidGameIdThrowsException() {
		var interestInputMap = Map.of(INVALID_GAME_ID, InterestLevel.NOOB);
		
		assertThrows(NotFoundException.class, 
						() -> gameMapper.mapGameIdToGame(interestInputMap));
	}
}
