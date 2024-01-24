package com.example.gamer.directory.domain.dtos;

import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.CREDIT;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAMER_ID;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAMER_NAME;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAME_ID;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAME_NAME;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.INTEREST_LEVEL;
import static com.example.gamer.directory.utils.Utils.toInt;
import static com.example.gamer.directory.utils.Utils.toLong;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MaxCreditGameDTO {
	@Schema(description = "ID of the Game", example = "1")
	private Integer gameId;
	
	@Schema(description = "Name of the Game", example = "fortnite")
	private String gameName;
	
	@Schema(description = "Level of interest shown in the game", example = "noob")
	private String level;
	
	@Schema(description = "Gamer with max credit or top scorer")
	private TopScore topScore = new TopScore();
	
	public MaxCreditGameDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.gameId = toInt(tuple[aliasToIndexMap.get(GAME_ID)]);
		this.gameName = String.valueOf(tuple[aliasToIndexMap.get(GAME_NAME)]);
		this.level = String.valueOf(tuple[aliasToIndexMap.get(INTEREST_LEVEL)]);
		
		topScore.getGamer().setId(toLong(tuple[aliasToIndexMap.get(GAMER_ID)]));
		topScore.getGamer().setName(String.valueOf(tuple[aliasToIndexMap.get(GAMER_NAME)]));
		
		topScore.setCredit(toInt(tuple[aliasToIndexMap.get(CREDIT)]));
	}
	
	@Data
	static class TopScore {
		@Data
		static class TopScorer {
			@Schema(description = "ID of the Gamer", example = "1")
			private Long id;
	
			@Schema(description = "Name of the Gamer", example = "XXX YYYY")
			private String name;
		}
		@Schema(description = "Top Scorer")
		private TopScorer gamer = new TopScorer();
		
		@Schema(description = "Credit awarded to him for the game", example = "10")
		private Integer credit;
	}
}
