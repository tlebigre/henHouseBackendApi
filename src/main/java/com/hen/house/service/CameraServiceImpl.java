package com.hen.house.service;

import com.hen.house.dto.CameraDto;
import com.hen.house.dto.SimpleCameraDto;
import com.hen.house.mapper.CameraMapper;
import com.hen.house.model.Camera;
import com.hen.house.repository.CameraRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CameraServiceImpl implements CameraService {

	private final CameraRepository cameraRepository;
	private final CameraMapper cameraMapper;

	public CameraServiceImpl(CameraRepository cameraRepository, CameraMapper cameraMapper) {
		this.cameraRepository = cameraRepository;
        this.cameraMapper = cameraMapper;
    }
	
	@Override
	public CameraDto getCamera(int id) {
		return cameraMapper.cameraToDto(cameraRepository.get(id).orElse(new Camera()));
	}

	@Override
	public List<CameraDto> getAllCamera() {
		return cameraMapper.cameraListToDto(cameraRepository.getAll());
	}
	
	@Override
	public void saveCamera(SimpleCameraDto simpleCameraDto) {
		cameraRepository.save(cameraMapper.dtoToCamera(simpleCameraDto));
	}
	
	@Override
	public void saveCamera(CameraDto cameraDto) {
		cameraRepository.save(cameraMapper.dtoToCamera(cameraDto));
	}

	@Override
	public void removeCamera(int id) {
		cameraRepository.remove(id);
	}
}
