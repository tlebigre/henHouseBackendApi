package com.hen.house.model;

public enum EngineAction {
	UP("engineUp"), DOWN("engineDown"), FORCE_UP("forceEngineUp"), FORCE_DOWN("forceEngineDown");

	private String action;

	private EngineAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return action;
	}

	public boolean isUp() {
		return this == EngineAction.UP || this == EngineAction.FORCE_UP;
	}
	
	public boolean isForce() {
		return this == EngineAction.FORCE_UP || this == EngineAction.FORCE_DOWN;
	}
}
