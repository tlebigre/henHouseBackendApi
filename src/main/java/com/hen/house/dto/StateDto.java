package com.hen.house.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class StateDto {
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
