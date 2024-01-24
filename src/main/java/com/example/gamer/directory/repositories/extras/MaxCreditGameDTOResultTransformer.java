package com.example.gamer.directory.repositories.extras;

import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.GAME_ID;
import static com.example.gamer.directory.repositories.extras.MaxCreditQueryConstants.INTEREST_LEVEL;
import static com.example.gamer.directory.utils.Utils.toInt;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.query.TupleTransformer;

import com.example.gamer.directory.domain.dtos.MaxCreditGameDTO;

public class MaxCreditGameDTOResultTransformer implements TupleTransformer<MaxCreditGameDTO> {
	
	private Map<GameID, MaxCreditGameDTO> maxCreditGameMap = new HashMap<>();
	
	@Override
	public MaxCreditGameDTO transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasesToIndexMap(aliases);
		
		var gameId = new GameID(
						toInt(tuple[aliasToIndexMap.get(GAME_ID)]), 
						String.valueOf(tuple[aliasToIndexMap.get(INTEREST_LEVEL)])
						);
		
		var maxCreditGameDTO = 
				maxCreditGameMap
					.computeIfAbsent(gameId, __ -> new MaxCreditGameDTO(tuple, aliasToIndexMap));
		return maxCreditGameDTO;
	}

	private Map<String, Integer> aliasesToIndexMap(String[] aliases) {
		Map<String, Integer> aliasToIndexMap = new HashMap<>();
		for (int i = 0; i < aliases.length; i++) {
			aliasToIndexMap.put(aliases[i].toLowerCase(), i);
		}
		return aliasToIndexMap;
	}
	
	static record GameID(Integer gameId, String level) {}
}
