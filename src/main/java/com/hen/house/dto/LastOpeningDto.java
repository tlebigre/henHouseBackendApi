package com.hen.house.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class LastOpeningDto {
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
