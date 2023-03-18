package com.henhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.henhouse.dto.LastOpeningDto;
import com.henhouse.service.HenHouseWhileLoopService;
import com.henhouse.service.LastOpeningService;

@RestController
public class OpeningController {
	
	@Autowired
	private LastOpeningService lastOpeningService;
	
	@Autowired
	private HenHouseWhileLoopService henHouseWhileLoopService;
	
	@CrossOrigin
	@PostMapping("/lastOpening")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveLastOpening(@RequestBody LastOpeningDto stateDto) {
		lastOpeningService.saveLastOpening(stateDto);
	}

	@CrossOrigin
	@GetMapping("/lastOpening")
	public LastOpeningDto getLastOpening() {
		return lastOpeningService.getLastOpening();
	}
	
	@CrossOrigin
	@GetMapping("/open")
	@ResponseStatus(HttpStatus.OK)
	public void open() {
		henHouseWhileLoopService.open();
	}
	
	@CrossOrigin
	@GetMapping("/close")
	@ResponseStatus(HttpStatus.OK)
	public void close() {
		henHouseWhileLoopService.close();
	}
	
}
