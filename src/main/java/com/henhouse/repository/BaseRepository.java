package com.henhouse.repository;

import java.util.Optional;

public interface BaseRepository<T> {
	Optional<T> get();

	void save(T object);
}
