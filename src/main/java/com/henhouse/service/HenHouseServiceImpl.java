package com.henhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henhouse.dto.DateTimeDto;
import com.henhouse.dto.HenHouseDto;
import com.henhouse.dto.StateDto;
import com.henhouse.mapper.HenHouseMapper;
import com.henhouse.model.DateTime;
import com.henhouse.model.HenHouse;
import com.henhouse.repository.DateTimeRepository;
import com.henhouse.repository.GpioRepository;
import com.henhouse.repository.HenHouseRepository;

@Service
public class HenHouseServiceImpl implements HenHouseService {

	@Autowired
	private HenHouseRepository henHouseRepository;
	@Autowired
	private DateTimeRepository dateTimeRepository;
	@Autowired
	private GpioRepository gpioRepository;

	@Autowired
	private HenHouseWhileLoopService henHouseWhileLoopService;

	@Override
	public HenHouseDto getHenHouse() {
		return HenHouseMapper.INSTANCE.henHouseToDto(henHouseRepository.get().orElse(new HenHouse()),
				dateTimeRepository.get().orElse(new DateTime()), gpioRepository.getEngineState());
	}

	@Override
	public StateDto getState() {
		return mapToStateDto(gpioRepository.getEngineState());
	}

	@Override
	public void saveHenHouse(HenHouseDto henHouseDto) {
		gpioRepository.saveEngineState(henHouseDto.getState());
		dateTimeRepository.save(HenHouseMapper.INSTANCE.dtoToDateTime(henHouseDto));
		henHouseRepository.save(HenHouseMapper.INSTANCE.dtoToHenHouse(henHouseDto));
		henHouseWhileLoopService.update();
	}

	@Override
	public void saveState(StateDto stateDto) {
		gpioRepository.saveEngineState(stateDto.getState());
		henHouseWhileLoopService.update();
	}

	@Override
	public DateTimeDto getDateTime() {
		return dateTimeRepository.get().map(HenHouseServiceImpl::mapToDateTimeDto).orElse(new DateTimeDto());
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
