package com.hen.house.controller;

import com.hen.house.dto.CameraDto;
import com.hen.house.dto.SimpleCameraDto;
import com.hen.house.service.CameraService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

import java.util.List;

@Controller("/camera")
@CrossOrigin(allowedOrigins = "http://localhost:5173")
public class CameraController {

	private final CameraService cameraService;

	public CameraController(CameraService cameraService) {
		this.cameraService = cameraService;
	}

	@Post
	public HttpResponse<Void> saveCamera(@Body SimpleCameraDto simpleCameraDto) {
		cameraService.saveCamera(simpleCameraDto);
		return HttpResponse.ok();
	}

	@Put
	public HttpResponse<Void> editCamera(@Body CameraDto cameraDto) {
		cameraService.saveCamera(cameraDto);
		return HttpResponse.ok();
	}

	@Get("/{id}")
	public CameraDto getCamera(@PathVariable int id) {
		return cameraService.getCamera(id);
	}

	@Get("/all")
	public List<CameraDto> getAllCamera() {
		return cameraService.getAllCamera();
	}

	@Delete("/{id}")
	public HttpResponse<Void> removeCamera(@PathVariable int id) {
		cameraService.removeCamera(id);
		return HttpResponse.noContent();
	}
}
