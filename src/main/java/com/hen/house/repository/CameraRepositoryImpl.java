package com.hen.house.repository;

import com.hen.house.model.Camera;
import io.micronaut.data.annotation.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CameraRepositoryImpl implements CameraRepository {

	JsonListRepository<Camera> cameraRepository = new JsonListRepositoryImpl<>(Camera.class);

	@Override
	public Optional<Camera> get(int id) {
		return cameraRepository.get(id);
	}

	@Override
	public List<Camera> getAll() {
		return cameraRepository.getAll();
	}

	@Override
	public void save(Camera camera) {
		cameraRepository.save(camera);
	}

	@Override
	public void remove(int id) {
		cameraRepository.remove(id);
	}
}
