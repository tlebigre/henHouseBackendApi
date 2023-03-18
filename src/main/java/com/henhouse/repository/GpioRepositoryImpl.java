package com.henhouse.repository;

import org.springframework.stereotype.Repository;

import com.henhouse.board.BoardGrpc;
import com.henhouse.board.EngineRequest;
import com.henhouse.board.GpioGetRequest;
import com.henhouse.board.GpioSetRequest;
import com.henhouse.board.StateGetRequest;
import com.henhouse.board.StateSetRequest;
import com.henhouse.board.BoardGrpc.BoardBlockingStub;
import com.henhouse.model.EngineAction;
import com.henhouse.service.ConstantsService;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Repository
public class GpioRepositoryImpl implements GpioRepository {

	private final BoardBlockingStub blockingStub;

	public GpioRepositoryImpl() {
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
}
