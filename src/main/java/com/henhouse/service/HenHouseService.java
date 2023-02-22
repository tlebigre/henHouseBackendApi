package com.henhouse.service;

import com.henhouse.dto.DateTimeDto;
import com.henhouse.dto.HenHouseDto;
import com.henhouse.dto.StateDto;

public interface HenHouseService {
	HenHouseDto getHenHouse();

	StateDto getState();

	void saveHenHouse(HenHouseDto henHouseDto);

	void saveState(StateDto stateDto);

	DateTimeDto getDateTime();
}
