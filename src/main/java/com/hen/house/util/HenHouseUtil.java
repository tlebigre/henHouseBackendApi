package com.hen.house.util;

import java.time.LocalTime;

public class HenHouseUtil {

	private HenHouseUtil() {}

	public static boolean timeBetween(String timeToCompare, String minTime, String maxTime) {
		return LocalTime.parse(timeToCompare).isBefore(LocalTime.parse(maxTime))
				&& LocalTime.parse(timeToCompare).isAfter(LocalTime.parse(minTime));
	}
}
