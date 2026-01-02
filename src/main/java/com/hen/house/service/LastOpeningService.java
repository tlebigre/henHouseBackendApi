package com.hen.house.service;

import com.hen.house.dto.LastOpeningDto;

public interface LastOpeningService {

	LastOpeningDto getLastOpening();

	void saveLastOpening(LastOpeningDto lastOpeningDto);
}
