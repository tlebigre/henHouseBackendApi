package com.henhouse.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.henhouse.model.LastOpening;

@Repository
public class LastOpeningRepositoryImpl implements LastOpeningRepository {
	BaseRepository<LastOpening> lastOpeningRepositoryImpl = new JsonRepositoryImpl<>(LastOpening.class);

	@Override
	public Optional<LastOpening> get() {
		return lastOpeningRepositoryImpl.get();
	}

	@Override
	public void save(LastOpening lastOpening) {
		lastOpeningRepositoryImpl.save(lastOpening);
	}
}
