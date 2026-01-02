package com.hen.house.model;

public class HenHouse {
	private boolean getUpMorning = true;
	private boolean stopLimits = true;
	private boolean stopEngine = true;
	private String hourOpening = "08:00";
	private String hourOpeningMax = "18:00";
	private int topLimit = 1000;
	private int bottomLimit = 0;
	private int speed = 5;

	public HenHouse() {
		super();
	}

	public HenHouse(boolean getUpMorning, boolean stopLimits, boolean stopEngine, String hourOpening,
			String hourOpeningMax, int topLimit, int bottomLimit, int speed) {
		super();
		this.getUpMorning = getUpMorning;
		this.stopLimits = stopLimits;
		this.stopEngine = stopEngine;
		this.hourOpening = hourOpening;
		this.hourOpeningMax = hourOpeningMax;
		this.topLimit = topLimit;
		this.bottomLimit = bottomLimit;
		this.speed = speed;
	}

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

}
