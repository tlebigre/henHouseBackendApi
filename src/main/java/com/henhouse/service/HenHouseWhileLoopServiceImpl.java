package com.henhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.henhouse.model.DateTime;
import com.henhouse.model.EngineAction;
import com.henhouse.model.HenHouse;
import com.henhouse.model.LastOpening;
import com.henhouse.repository.DateTimeRepository;
import com.henhouse.repository.GpioRepository;
import com.henhouse.repository.HenHouseRepository;
import com.henhouse.repository.LastOpeningRepository;
import com.henhouse.util.HenHouseUtil;

@Service
public class HenHouseWhileLoopServiceImpl implements HenHouseWhileLoopService {

	@Autowired
	private HenHouseRepository henHouseRepository;
	@Autowired
	private DateTimeRepository dateTimeRepository;
	@Autowired
	private LastOpeningRepository lastOpeningRepository;
	@Autowired
	private GpioRepository gpioRepository;

	private static int ENGINE_RUN_GPIO = 13;
	private static int ENGINE_DIRECTION_GPIO = 19;
	private static int ENGINE_STOP_GPIO = 26;
	private static int UP_GPIO = 17;
	private static int DOWN_GPIO = 27;

	private boolean getUpMorning;
	private boolean stopLimits;
	private boolean stopEngine;
	private String hourOpening;
	private String hourOpeningMax;
	private int topLimit;
	private int bottomLimit;
	private int speed;
	private String date;
	private String time;
	private String lastOpening;

	private boolean firstInit = true;
	private boolean blockOpen = false;
	private boolean stopEngineState = false;
	private boolean forceUp = false;
	private boolean forceDown = false;

	@Override
	@Scheduled(fixedRate = 1)
	public void whileLoop() {
		if (firstInit) {
			update();
			stopEngineState = stopEngine;
			firstInit = false;
			blockOpen = false;
		}

		if (forceUp) {
			up(true);
			forceUp = false;
		}
		if (forceDown) {
			down(true);
			forceDown = false;
		}

		lastOpeningRepository.get().ifPresent(o -> lastOpening = o.getDate());

		if (gpioRepository.getGpioState(UP_GPIO))
			up(false);

		if (gpioRepository.getGpioState(DOWN_GPIO))
			down(false);
		else
			blockOpen = false;

		if (getUpMorning && HenHouseUtil.timeBetween(time, hourOpening, hourOpeningMax) && !date.equals(lastOpening))
			morningUp();

		if (!stopEngineState && (stopEngine || stopLimitsCheck())) {
			gpioRepository.saveGpioState(ENGINE_STOP_GPIO, true);
			stopEngineState = true;
		}
	}

	private void morningUp() {
		up(true);
		blockOpen = true;
		lastOpeningRepository.save(new LastOpening(date));
	}

	private boolean stopLimitsCheck() {
		if (!stopLimits)
			return false;

		int engineState = gpioRepository.getEngineState();
		return engineState == topLimit || engineState == bottomLimit;
	}

	private void up(boolean force) {
		if (gpioRepository.getEngineState() >= topLimit)
			return;

		upOrDown(force, topLimit, false);

	}

	private void down(boolean force) {
		if (blockOpen || gpioRepository.getEngineState() <= bottomLimit)
			return;

		upOrDown(force, bottomLimit, true);
	}

	private void upOrDown(boolean force, int limit, boolean isDown) {
		gpioRepository.saveGpioState(ENGINE_STOP_GPIO, false);
		stopEngineState = false;
		gpioRepository.saveGpioState(ENGINE_DIRECTION_GPIO, isDown);
		if (force)
			gpioRepository.engineRun(ENGINE_RUN_GPIO, speed, limit,
					isDown ? EngineAction.FORCE_DOWN : EngineAction.FORCE_UP);
		else
			gpioRepository.engineRun(ENGINE_RUN_GPIO, speed, isDown ? DOWN_GPIO : UP_GPIO, limit,
					isDown ? EngineAction.DOWN : EngineAction.UP);
	}

	@Override
	public void update() {
		HenHouse henHouseR = henHouseRepository.get().orElse(new HenHouse());
		DateTime dateTimeR = dateTimeRepository.get().orElse(new DateTime());
		LastOpening lastOpeningR = lastOpeningRepository.get().orElse(new LastOpening());

		getUpMorning = henHouseR.isGetUpMorning();
		stopLimits = henHouseR.isStopLimits();
		stopEngine = henHouseR.isStopEngine();
		bottomLimit = henHouseR.getBottomLimit();
		hourOpening = henHouseR.getHourOpening();
		hourOpeningMax = henHouseR.getHourOpeningMax();
		speed = henHouseR.getSpeed();
		topLimit = henHouseR.getTopLimit();
		date = dateTimeR.getDate();
		time = dateTimeR.getTime();
		lastOpening = lastOpeningR.getDate();
	}

	@Override
	public void open() {
		forceUp = true;
	}

	@Override
	public void close() {
		forceDown = true;
	}
}
