package com.henhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henhouse.dto.CameraDto;
import com.henhouse.dto.SimpleCameraDto;
import com.henhouse.mapper.CameraMapper;
import com.henhouse.model.Camera;
import com.henhouse.repository.CameraRepository;

@Service
public class CameraServiceImpl implements CameraService {

	@Autowired
	private CameraRepository cameraRepository;
	
	@Override
	public CameraDto getCamera(int id) {
		return CameraMapper.INSTANCE.cameraToDto(cameraRepository.get(id).orElse(new Camera()));
	}

	@Override
	public List<CameraDto> getAllCamera() {
		return CameraMapper.INSTANCE.cameraListToDto(cameraRepository.getAll());
	}
	
	@Override
	public void saveCamera(SimpleCameraDto simpleCameraDto) {
		cameraRepository.save(CameraMapper.INSTANCE.dtoToCamera(simpleCameraDto));
	}
	
	@Override
	public void saveCamera(CameraDto cameraDto) {
		cameraRepository.save(CameraMapper.INSTANCE.dtoToCamera(cameraDto));
	}

	@Override
	public void removeCamera(int id) {
		cameraRepository.remove(id);
	}
}
