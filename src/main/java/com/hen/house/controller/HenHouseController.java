package com.hen.house.controller;

import com.hen.house.dto.HenHouseDto;
import com.hen.house.dto.DateTimeDto;
import com.hen.house.dto.StateDto;
import com.hen.house.service.HenHouseService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

@Controller("/henHouse")
@CrossOrigin(allowedOrigins = "http://localhost:5173")
public class HenHouseController {

	private final HenHouseService henHouseService;

	public HenHouseController(HenHouseService henHouseService) {
		this.henHouseService = henHouseService;
	}

	@Post("/henHouse")
	public HttpResponse<Void> saveHenHouse(@Body HenHouseDto henHouseDto) {
		henHouseService.saveHenHouse(henHouseDto);
		return HttpResponse.ok();
	}

	@Post("/state")
	public HttpResponse<Void> saveState(@Body StateDto stateDto) {
		henHouseService.saveState(stateDto);
		return HttpResponse.ok();
	}

	@Get("/henHouse")
	public HenHouseDto getHenHouse() {
		return henHouseService.getHenHouse();
	}

	@Get("/state")
	public StateDto getState() {
		return henHouseService.getState();
	}

	@Get("/dateTime")
	public DateTimeDto getDateTime() {
		return henHouseService.getDateTime();
	}
}
