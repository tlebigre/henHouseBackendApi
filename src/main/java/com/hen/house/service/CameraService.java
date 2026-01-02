package com.hen.house.service;

import com.hen.house.dto.CameraDto;
import com.hen.house.dto.SimpleCameraDto;

import java.util.List;

public interface CameraService {
	CameraDto getCamera(int id);
	
	List<CameraDto> getAllCamera();
	
	void saveCamera(SimpleCameraDto simpleCameraDto);
	
	void saveCamera(CameraDto cameraDto);
	
	void removeCamera(int id);
}
