package com.henhouse.service;

import java.util.List;

import com.henhouse.dto.CameraDto;
import com.henhouse.dto.SimpleCameraDto;

public interface CameraService {
	CameraDto getCamera(int id);
	
	List<CameraDto> getAllCamera();
	
	void saveCamera(SimpleCameraDto simpleCameraDto);
	
	void saveCamera(CameraDto cameraDto);
	
	void removeCamera(int id);
}
