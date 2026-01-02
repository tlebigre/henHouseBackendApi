package com.hen.house.repository;

import java.util.List;
import java.util.Optional;

public interface JsonListRepository<T> {
	Optional<T> get(int id);
	
	List<T> getAll();
	
	void save(T object);
	
	void remove(int id);
}
