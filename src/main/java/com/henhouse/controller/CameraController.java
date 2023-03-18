package com.henhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.henhouse.dto.CameraDto;
import com.henhouse.dto.SimpleCameraDto;
import com.henhouse.service.CameraService;

@RestController
public class CameraController {

	@Autowired
	private CameraService cameraService;

	@CrossOrigin
	@PostMapping("/camera")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCamera(@RequestBody SimpleCameraDto simpleCameraDto) {
		cameraService.saveCamera(simpleCameraDto);
	}
	
	@CrossOrigin
	@PutMapping("/camera")
	@ResponseStatus(HttpStatus.OK)
	public void editCamera(@RequestBody CameraDto cameraDto) {
		cameraService.saveCamera(cameraDto);
	}

	@CrossOrigin
	@GetMapping(value = "/camera/{id}")
	public CameraDto getCamera(@PathVariable(value = "id") int id) {
		return cameraService.getCamera(id);
	}

	@CrossOrigin
	@GetMapping("/cameras")
	public List<CameraDto> getAllCamera() {
		return cameraService.getAllCamera();
	}

	@CrossOrigin
	@DeleteMapping( "/camera/{id}" )
	public void removeCamera(@PathVariable(value = "id") int id) {
		cameraService.removeCamera(id);
	}
}
