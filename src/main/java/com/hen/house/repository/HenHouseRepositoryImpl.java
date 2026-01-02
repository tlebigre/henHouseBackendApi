package com.hen.house.repository;

import com.hen.house.model.HenHouse;
import io.micronaut.data.annotation.Repository;

import java.util.Optional;

@Repository
public class HenHouseRepositoryImpl implements HenHouseRepository {

	BaseRepository<HenHouse> henHouseRepository = new JsonRepositoryImpl<>(HenHouse.class);

	@Override
	public Optional<HenHouse> get() {
		return henHouseRepository.get();
	}

	@Override
	public void save(HenHouse henHouse) {
		henHouseRepository.save(henHouse);
	}
}
