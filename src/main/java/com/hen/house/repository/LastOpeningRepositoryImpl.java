package com.hen.house.repository;

import com.hen.house.model.LastOpening;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

@Repository
public class LastOpeningRepositoryImpl implements LastOpeningRepository {
	BaseRepository<LastOpening> lastOpeningRepository = new JsonRepositoryImpl<>(LastOpening.class);

	@Override
	public Optional<LastOpening> get() {
		return lastOpeningRepository.get();
	}

	@Override
	public void save(LastOpening lastOpening) {
		lastOpeningRepository.save(lastOpening);
	}
}
