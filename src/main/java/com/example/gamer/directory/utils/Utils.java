package com.example.gamer.directory.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class Utils {
	public static Integer toInt(Object intObj) {
		var intStr = String.valueOf(intObj);
		return NumberUtils.toInt(intStr);
	}
	
	public static Long toLong(Object longObj) {
		var longStr = String.valueOf(longObj);
		return NumberUtils.toLong(longStr);
	}
}
