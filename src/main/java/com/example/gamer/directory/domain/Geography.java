package com.example.gamer.directory.domain;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Geography {
	ASIA, EUROPE, AUSTRALIA, USA, AFRICA, CANADA;
	
	@JsonCreator
	public static Geography fromString(String geo) {
		return StringUtils.isBlank(geo) 
				? null 
				: valueOf(geo.toUpperCase());
	}
	
	@JsonValue
	public String toLowerCase() {
		return name().toLowerCase();
	}
}
