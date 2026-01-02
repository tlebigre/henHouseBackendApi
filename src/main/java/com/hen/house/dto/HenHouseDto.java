package com.hen.house.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HenHouseDto {
	private boolean getUpMorning;
	private boolean stopLimits;
	private boolean stopEngine;
	private String hourOpening;
	private String hourOpeningMax;
	private int state;
	private int topLimit;
	private int bottomLimit;
	private int speed;
	private String date;
	private String time;

	public boolean isGetUpMorning() {
		return getUpMorning;
	}

	public void setGetUpMorning(boolean getUpMorning) {
		this.getUpMorning = getUpMorning;
	}

	public boolean isStopLimits() {
		return stopLimits;
	}

	public void setStopLimits(boolean stopLimits) {
		this.stopLimits = stopLimits;
	}

	public boolean isStopEngine() {
		return stopEngine;
	}

	public void setStopEngine(boolean stopEngine) {
		this.stopEngine = stopEngine;
	}

	public String getHourOpening() {
		return hourOpening;
	}

	public void setHourOpening(String hourOpening) {
		this.hourOpening = hourOpening;
	}

	public String getHourOpeningMax() {
		return hourOpeningMax;
	}

	public void setHourOpeningMax(String hourOpeningMax) {
		this.hourOpeningMax = hourOpeningMax;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTopLimit() {
		return topLimit;
	}

	public void setTopLimit(int topLimit) {
		this.topLimit = topLimit;
	}

	public int getBottomLimit() {
		return bottomLimit;
	}

	public void setBottomLimit(int bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

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
