package com.henhouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.henhouse.model.Camera;

@Repository
public class CameraRepositoryImpl implements CameraRepository {

	JsonListRepository<Camera> cameraRepositoryImpl = new JsonListRepositoryImpl<>(Camera.class);

	@Override
	public Optional<Camera> get(int id) {
		return cameraRepositoryImpl.get(id);
	}

	@Override
	public List<Camera> getAll() {
		return cameraRepositoryImpl.getAll();
	}

	@Override
	public void save(Camera camera) {
		cameraRepositoryImpl.save(camera);
	}

	@Override
	public void remove(int id) {
		cameraRepositoryImpl.remove(id);
	}
}
