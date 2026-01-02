package com.hen.house.service;

import com.hen.house.model.DateTime;
import com.hen.house.model.EngineAction;
import com.hen.house.model.HenHouse;
import com.hen.house.model.LastOpening;
import com.hen.house.repository.GpioGrpcClient;
import com.hen.house.repository.HenHouseRepository;
import com.hen.house.repository.LastOpeningRepository;
import com.hen.house.util.HenHouseUtil;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

@Singleton
public class HenHouseWhileLoopServiceImpl implements HenHouseWhileLoopService {
	private final GpioGrpcClient gpioGrpcClient;
	private final HenHouseRepository henHouseRepository;
	private final LastOpeningRepository lastOpeningRepository;

	private static final int ENGINE_RUN_GPIO = 13;
	private static final int ENGINE_DIRECTION_GPIO = 19;
	private static final int ENGINE_STOP_GPIO = 26;
	private static final int UP_GPIO = 17;
	private static final int DOWN_GPIO = 27;

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

    public HenHouseWhileLoopServiceImpl(GpioGrpcClient gpioGrpcClient, HenHouseRepository henHouseRepository, LastOpeningRepository lastOpeningRepository) {
        this.gpioGrpcClient = gpioGrpcClient;
        this.henHouseRepository = henHouseRepository;
        this.lastOpeningRepository = lastOpeningRepository;
    }

    @Override
	@Scheduled(fixedDelay = "1s")
	public void whileLoop() {
		if (firstInit) {
			update();
			stopEngineState = stopEngine;
			firstInit = false;
			blockOpen = false;
		}
		else
		{
			lastOpeningRepository.get().ifPresent(o -> lastOpening = o.getDate());
			gpioGrpcClient.getDate().ifPresent(d -> {
				date = d.getDate();
				time = d.getTime();
			});
		}

		if (forceUp) {
			up(true);
			forceUp = false;
		}
		if (forceDown) {
			down(true);
			forceDown = false;
		}

		if (gpioGrpcClient.getGpioState(UP_GPIO))
			up(false);

		if (gpioGrpcClient.getGpioState(DOWN_GPIO))
			down(false);
		else
			blockOpen = false;

		if (getUpMorning && HenHouseUtil.timeBetween(time, hourOpening, hourOpeningMax) && !date.equals(lastOpening))
			morningUp();

		if (!stopEngineState && (stopEngine || stopLimitsCheck())) {
			gpioGrpcClient.saveGpioState(ENGINE_STOP_GPIO, true);
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

		int engineState = gpioGrpcClient.getEngineState();
		return engineState == topLimit || engineState == bottomLimit;
	}

	private void up(boolean force) {
		if (gpioGrpcClient.getEngineState() >= topLimit)
			return;

		upOrDown(force, topLimit, false);

	}

	private void down(boolean force) {
		if (blockOpen || gpioGrpcClient.getEngineState() <= bottomLimit)
			return;

		upOrDown(force, bottomLimit, true);
	}

	private void upOrDown(boolean force, int limit, boolean isDown) {
		gpioGrpcClient.saveGpioState(ENGINE_STOP_GPIO, false);
		stopEngineState = false;
		gpioGrpcClient.saveGpioState(ENGINE_DIRECTION_GPIO, isDown);
		if (force)
			gpioGrpcClient.engineRun(ENGINE_RUN_GPIO, speed, limit,
					isDown ? EngineAction.FORCE_DOWN : EngineAction.FORCE_UP);
		else
			gpioGrpcClient.engineRun(ENGINE_RUN_GPIO, speed, isDown ? DOWN_GPIO : UP_GPIO, limit,
					isDown ? EngineAction.DOWN : EngineAction.UP);
	}

	@Override
	public void update() {
		HenHouse henHouseR = henHouseRepository.get().orElse(new HenHouse());
		DateTime dateTimeR = gpioGrpcClient.getDate().orElse(new DateTime());
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
