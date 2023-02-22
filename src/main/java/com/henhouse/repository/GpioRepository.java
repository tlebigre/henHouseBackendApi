package com.henhouse.repository;

import com.henhouse.model.EngineAction;

public interface GpioRepository {

	boolean getGpioState(int gpio);

	void saveGpioState(int gpio, boolean state);

	void engineRun(int gpio, int speed, int buttonGpio, int limit, EngineAction engineAction);

	void engineRun(int gpio, int speed, int limit, EngineAction engineAction);

	int getEngineState();

	void saveEngineState(int state);
}
