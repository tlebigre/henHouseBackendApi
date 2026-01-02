package com.hen.house.mapper;

import com.hen.house.dto.CameraDto;
import com.hen.house.model.Camera;
import com.hen.house.dto.SimpleCameraDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "jsr330")
public interface CameraMapper {
	CameraDto cameraToDto(Camera camera);

	List<CameraDto> cameraListToDto(List<Camera> camera);

	@Mapping(target = "id", ignore = true)
	Camera dtoToCamera(SimpleCameraDto cameraDto);

	Camera dtoToCamera(CameraDto cameraDto);
}
