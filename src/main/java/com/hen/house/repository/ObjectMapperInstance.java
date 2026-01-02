package com.hen.house.repository;


import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperInstance {

	private ObjectMapperInstance() {}

	private static ObjectMapper objectMapper = null;

	public static ObjectMapper getInstance() {
		if (objectMapper == null)
			objectMapper = new ObjectMapper();

		return objectMapper;
	}
}
