package com.hen.house.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class DateTimeDto {
	private String date;
	private String time;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
