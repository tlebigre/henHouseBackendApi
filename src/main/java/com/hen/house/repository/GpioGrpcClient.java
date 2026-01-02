package com.hen.house.repository;

import com.hen.house.model.DateTime;
import com.hen.house.model.EngineAction;

import java.util.Optional;

public interface GpioGrpcClient {

	boolean getGpioState(int gpio);

	void saveGpioState(int gpio, boolean state);

	void engineRun(int gpio, int speed, int buttonGpio, int limit, EngineAction engineAction);

	void engineRun(int gpio, int speed, int limit, EngineAction engineAction);

	int getEngineState();

	void saveEngineState(int state);

	Optional<DateTime> getDate();

	void saveDate(DateTime dateTime);
}
