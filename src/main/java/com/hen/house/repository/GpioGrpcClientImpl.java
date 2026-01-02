package com.hen.house.repository;

import com.hen.house.board.*;
import com.hen.house.model.DateTime;
import com.hen.house.service.ConstantsService;
import com.hen.house.model.EngineAction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Singleton
public class GpioGrpcClientImpl implements GpioGrpcClient {

	private static final Logger LOGGER = LogManager.getLogger(GpioGrpcClientImpl.class);

	private final BoardGrpc.BoardBlockingStub blockingStub;

	public GpioGrpcClientImpl() {
		ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forAddress(ConstantsService.GPIO_API_URL, 9000)
				.usePlaintext();
		ManagedChannel channel = channelBuilder.build();
		blockingStub = BoardGrpc.newBlockingStub(channel);
	}

	@Override
	public boolean getGpioState(int gpio) {
		GpioGetRequest gpioGetRequest = GpioGetRequest.newBuilder().setGpio(gpio).build();
		return blockingStub.getGpio(gpioGetRequest).getValue();
	}

	@Override
	public void saveGpioState(int gpio, boolean state) {
		GpioSetRequest gpioSetRequest = GpioSetRequest.newBuilder().setGpio(gpio).setValue(state).build();
		blockingStub.setGpio(gpioSetRequest);
	}

	@Override
	public void engineRun(int gpio, int speed, int buttonGpio, int limit, EngineAction engineAction) {
		EngineRequest engineRequest = EngineRequest.newBuilder().setGpio(gpio).setSpeed(speed).setButtonGpio(buttonGpio)
				.setLimit(limit).setIsForce(engineAction.isForce()).setIsUp(engineAction.isUp()).build();
		blockingStub.engineUpOrDown(engineRequest);
	}

	@Override
	public void engineRun(int gpio, int speed, int limit, EngineAction engineAction) {
		engineRun(gpio, speed, 0, limit, engineAction);
	}

	@Override
	public int getEngineState() {
		StateGetRequest stateGetRequest = StateGetRequest.newBuilder().build();
		return blockingStub.getState(stateGetRequest).getValue();
	}

	@Override
	public void saveEngineState(int state) {
		StateSetRequest stateSetRequest = StateSetRequest.newBuilder().setValue(state).build();
		blockingStub.setState(stateSetRequest);
	}


	@Override
	public Optional<DateTime> getDate() {
		DateTimeGetRequest dateTimeGetRequest = DateTimeGetRequest.newBuilder().build();
		DateTimeReply dateTimeReply = blockingStub.getDateTime(dateTimeGetRequest);
		return Optional.of(new DateTime(dateTimeReply.getDate(),dateTimeReply.getTime()));
	}

	@Override
	public void saveDate(DateTime dateTime) {
		String date = dateTime.getDate();
		DateTimeSetRequest dateTimeSetRequest = DateTimeSetRequest.newBuilder().setDate(date).setTime(dateTime.getTime()).setDayOfWeek(getDayOfWeek(date)).build();
		blockingStub.setDateTime(dateTimeSetRequest);
	}

	private static int getDayOfWeek(String date) {
		Calendar calendar = Calendar.getInstance();
		parseDate(date).ifPresent(calendar::setTime);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	private static Optional<Date> parseDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return Optional.of(dateFormat.parse(date));
		} catch (ParseException e) {
			LOGGER.warn("Bad date format");
		}
		return Optional.empty();
	}
}
