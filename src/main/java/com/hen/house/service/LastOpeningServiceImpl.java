package com.hen.house.service;

import com.hen.house.dto.LastOpeningDto;
import com.hen.house.mapper.LastOpeningMapper;
import com.hen.house.model.LastOpening;
import com.hen.house.repository.LastOpeningRepository;
import jakarta.inject.Singleton;

@Singleton
public class LastOpeningServiceImpl implements LastOpeningService {

	private final HenHouseWhileLoopService henHouseWhileLoopService;

	private final LastOpeningRepository lastOpeningRepository;

	private final LastOpeningMapper lastOpeningMapper;

    public LastOpeningServiceImpl(HenHouseWhileLoopService henHouseWhileLoopService, LastOpeningRepository lastOpeningRepository, LastOpeningMapper lastOpeningMapper) {
        this.henHouseWhileLoopService = henHouseWhileLoopService;
        this.lastOpeningRepository = lastOpeningRepository;
        this.lastOpeningMapper = lastOpeningMapper;
    }

    @Override
	public LastOpeningDto getLastOpening() {
		return this.lastOpeningMapper.lastOpeningToDto(lastOpeningRepository.get().orElse(new LastOpening()));
	}

	@Override
	public void saveLastOpening(LastOpeningDto lastOpeningDto) {
		lastOpeningRepository.save(this.lastOpeningMapper.dtoToLastOpening(lastOpeningDto));
		henHouseWhileLoopService.update();
	}
}
