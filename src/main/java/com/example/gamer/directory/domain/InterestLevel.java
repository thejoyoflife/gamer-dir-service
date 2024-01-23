package com.example.gamer.directory.domain;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InterestLevel {
	NOOB, PRO, INVINCIBLE;
	
	@JsonCreator
	public static InterestLevel fromString(String intLevel) {
		return isBlank(intLevel) 
				? null 
				: valueOf(intLevel.toUpperCase());
	}
	
	@JsonValue
	public String toLowerCase() {
		return name().toLowerCase();
	}
}
