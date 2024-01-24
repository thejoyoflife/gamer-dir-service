package com.example.gamer.directory.domain.dtos;

import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.CREDIT;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAMER_ID;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAMER_NAME;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAME_ID;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAME_NAME;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.INTEREST_LEVEL;
import static com.example.gamer.directory.utils.Utils.toInt;
import static com.example.gamer.directory.utils.Utils.toLong;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class MaxCreditGameDTO {
	private Integer gameId;
	private String gameName;
	private String level;
	
	private TopScorer topScore = new TopScorer();
	
	public MaxCreditGameDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.gameId = toInt(tuple[aliasToIndexMap.get(GAME_ID)]);
		this.gameName = String.valueOf(tuple[aliasToIndexMap.get(GAME_NAME)]);
		this.level = String.valueOf(tuple[aliasToIndexMap.get(INTEREST_LEVEL)]);
		
		topScore.getGamer().put("id", toLong(tuple[aliasToIndexMap.get(GAMER_ID)]));
		topScore.getGamer().put("name", String.valueOf(tuple[aliasToIndexMap.get(GAMER_NAME)]));
		
		topScore.setCredit(toInt(tuple[aliasToIndexMap.get(CREDIT)]));
	}
	
	@Data
	public class TopScorer {
		private Map<String, Object> gamer = new HashMap<>();
		private Integer credit;
	}
}
