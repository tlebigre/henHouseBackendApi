package com.hen.house.controller;

import com.hen.house.dto.LastOpeningDto;
import com.hen.house.service.HenHouseWhileLoopService;
import com.hen.house.service.LastOpeningService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;

@Controller("/opening")
@CrossOrigin(allowedOrigins = "http://localhost:5173")
public class OpeningController {

	private final LastOpeningService lastOpeningService;
	private final HenHouseWhileLoopService henHouseWhileLoopService;

	public OpeningController(
			LastOpeningService lastOpeningService,
			HenHouseWhileLoopService henHouseWhileLoopService
	) {
		this.lastOpeningService = lastOpeningService;
		this.henHouseWhileLoopService = henHouseWhileLoopService;
	}

	@Post("/lastOpening")
	public HttpResponse<Void> saveLastOpening(@Body LastOpeningDto lastOpeningDto) {
		lastOpeningService.saveLastOpening(lastOpeningDto);
		return HttpResponse.ok();
	}

	@Get("/lastOpening")
	public LastOpeningDto getLastOpening() {
		return lastOpeningService.getLastOpening();
	}

	@Post("/open")
	public HttpResponse<Void> open() {
		henHouseWhileLoopService.open();
		return HttpResponse.ok();
	}

	@Post("/close")
	public HttpResponse<Void> close() {
		henHouseWhileLoopService.close();
		return HttpResponse.ok();
	}
}
