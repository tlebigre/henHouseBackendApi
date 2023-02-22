package com.henhouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.henhouse.dto.CameraDto;
import com.henhouse.dto.SimpleCameraDto;
import com.henhouse.model.Camera;
import org.mapstruct.Mapping;

@Mapper
public interface CameraMapper {
	CameraMapper INSTANCE = Mappers.getMapper(CameraMapper.class);

	CameraDto cameraToDto(Camera camera);

	List<CameraDto> cameraListToDto(List<Camera> camera);

	@Mapping(target = "id", ignore = true)
	Camera dtoToCamera(SimpleCameraDto cameraDto);

	Camera dtoToCamera(CameraDto cameraDto);

}
