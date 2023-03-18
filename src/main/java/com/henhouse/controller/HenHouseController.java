package com.henhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.henhouse.dto.DateTimeDto;
import com.henhouse.dto.HenHouseDto;
import com.henhouse.dto.StateDto;
import com.henhouse.service.HenHouseService;

@RestController
public class HenHouseController {

	@Autowired
	private HenHouseService henHouseService;

	@CrossOrigin
	@PostMapping("/henHouse")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveHenHouse(@RequestBody HenHouseDto henHouseDto) {
		henHouseService.saveHenHouse(henHouseDto);
	}

	@CrossOrigin
	@PostMapping("/state")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveState(@RequestBody StateDto stateDto) {
		henHouseService.saveState(stateDto);
	}

	@CrossOrigin
	@GetMapping("/henHouse")
	public HenHouseDto getHenHouse() {
		return henHouseService.getHenHouse();
	}

	@CrossOrigin
	@GetMapping("/state")
	public StateDto getState() {
		return henHouseService.getState();
	}
	
	@CrossOrigin
	@GetMapping("/dateTime")
	public DateTimeDto getDateTime() {
		return henHouseService.getDateTime();
	}
	
}
