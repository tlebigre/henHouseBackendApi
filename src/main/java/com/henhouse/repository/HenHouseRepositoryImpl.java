package com.henhouse.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.henhouse.model.HenHouse;

@Repository
public class HenHouseRepositoryImpl implements HenHouseRepository {

	BaseRepository<HenHouse> henHouseRepositoryImpl = new JsonRepositoryImpl<>(HenHouse.class);

	@Override
	public Optional<HenHouse> get() {
		return henHouseRepositoryImpl.get();
	}

	@Override
	public void save(HenHouse henHouse) {
		henHouseRepositoryImpl.save(henHouse);
	}
}
