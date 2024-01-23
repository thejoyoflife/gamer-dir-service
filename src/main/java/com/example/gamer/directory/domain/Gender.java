package com.example.gamer.directory.domain;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE, FEMALE, TRANSGENDER;
	
	@JsonCreator
	public static Gender fromString(String gender) {
		return isBlank(gender) 
				? null 
				: valueOf(gender.toUpperCase());
	}
	
	@JsonValue
	public String toLowerCase() {
		return name().toLowerCase();
	}
}
