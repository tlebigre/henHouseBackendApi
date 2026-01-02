package com.hen.house.service;

import com.hen.house.dto.HenHouseDto;
import com.hen.house.dto.DateTimeDto;
import com.hen.house.dto.StateDto;
import com.hen.house.mapper.HenHouseMapper;
import com.hen.house.model.DateTime;
import com.hen.house.model.HenHouse;
import com.hen.house.repository.GpioGrpcClient;
import com.hen.house.repository.HenHouseRepository;
import jakarta.inject.Singleton;

@Singleton
public class HenHouseServiceImpl implements HenHouseService {

	private final GpioGrpcClient gpioGrpcClient;

	private final HenHouseRepository henHouseRepository;

	private final HenHouseWhileLoopService henHouseWhileLoopService;

	private final HenHouseMapper henHouseMapper;

    public HenHouseServiceImpl(GpioGrpcClient gpioGrpcClient, HenHouseRepository henHouseRepository, HenHouseWhileLoopService henHouseWhileLoopService, HenHouseMapper henHouseMapper) {
        this.gpioGrpcClient = gpioGrpcClient;
        this.henHouseRepository = henHouseRepository;
        this.henHouseWhileLoopService = henHouseWhileLoopService;
        this.henHouseMapper = henHouseMapper;
    }

    @Override
	public HenHouseDto getHenHouse() {
		return this.henHouseMapper.henHouseToDto(henHouseRepository.get().orElse(new HenHouse()),
				gpioGrpcClient.getDate().orElse(new DateTime()), gpioGrpcClient.getEngineState());
	}

	@Override
	public StateDto getState() {
		return mapToStateDto(gpioGrpcClient.getEngineState());
	}

	@Override
	public void saveHenHouse(HenHouseDto henHouseDto) {
		gpioGrpcClient.saveEngineState(henHouseDto.getState());
		gpioGrpcClient.saveDate(this.henHouseMapper.dtoToDateTime(henHouseDto));
		henHouseRepository.save(this.henHouseMapper.dtoToHenHouse(henHouseDto));
		henHouseWhileLoopService.update();
	}

	@Override
	public void saveState(StateDto stateDto) {
		gpioGrpcClient.saveEngineState(stateDto.getState());
		henHouseWhileLoopService.update();
	}

	@Override
	public DateTimeDto getDateTime() {
		return gpioGrpcClient.getDate().map(HenHouseServiceImpl::mapToDateTimeDto).orElse(new DateTimeDto());
	}

	private static DateTimeDto mapToDateTimeDto(DateTime dateTime) {
		DateTimeDto dateTimeDto = new DateTimeDto();
		dateTimeDto.setDate(dateTime.getDate());
		dateTimeDto.setTime(dateTime.getTime());
		return dateTimeDto;
	}
	
	private static StateDto mapToStateDto(int state) {
		StateDto stateDto = new StateDto();
		stateDto.setState(state);
		return stateDto;
	}
}
