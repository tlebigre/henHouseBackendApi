package com.henhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henhouse.dto.LastOpeningDto;
import com.henhouse.mapper.LastOpeningMapper;
import com.henhouse.model.LastOpening;
import com.henhouse.repository.LastOpeningRepository;

@Service
public class LastOpeningServiceImpl implements LastOpeningService {
	
	@Autowired
	private LastOpeningRepository lastOpeningRepository;
	@Autowired
	private HenHouseWhileLoopService henHouseWhileLoopService;
	
	@Override
	public LastOpeningDto getLastOpening() {
		return LastOpeningMapper.INSTANCE.lastOpeningToDto(lastOpeningRepository.get().orElse(new LastOpening()));
	}

	@Override
	public void saveLastOpening(LastOpeningDto lastOpeningDto) {
		lastOpeningRepository.save(LastOpeningMapper.INSTANCE.dtoToLastOpening(lastOpeningDto));
		henHouseWhileLoopService.update();
	}
}
