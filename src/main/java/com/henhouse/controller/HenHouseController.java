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
	@PostMapping("/saveHenHouse")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveHenHouse(@RequestBody HenHouseDto henHouseDto) {
		henHouseService.saveHenHouse(henHouseDto);
	}

	@CrossOrigin
	@PostMapping("/saveState")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveState(@RequestBody StateDto stateDto) {
		henHouseService.saveState(stateDto);
	}

	@CrossOrigin
	@GetMapping("/getHenHouse")
	public HenHouseDto getHenHouse() {
		return henHouseService.getHenHouse();
	}

	@CrossOrigin
	@GetMapping("/getState")
	public StateDto getState() {
		return henHouseService.getState();
	}
	
	@CrossOrigin
	@GetMapping("/getDateTime")
	public DateTimeDto getDateTime() {
		return henHouseService.getDateTime();
	}
	
}
