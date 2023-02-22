package com.henhouse.service;

import com.henhouse.dto.LastOpeningDto;

public interface LastOpeningService {

	LastOpeningDto getLastOpening();

	void saveLastOpening(LastOpeningDto lastOpeningDto);
}
