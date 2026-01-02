package com.hen.house.service;

import com.hen.house.dto.HenHouseDto;
import com.hen.house.dto.DateTimeDto;
import com.hen.house.dto.StateDto;

public interface HenHouseService {
	HenHouseDto getHenHouse();

	StateDto getState();

	void saveHenHouse(HenHouseDto henHouseDto);

	void saveState(StateDto stateDto);

	DateTimeDto getDateTime();
}
