package com.henhouse.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperInstance {

	private static ObjectMapper OBJECT_MAPPER = null;

	public static ObjectMapper getInstance() {
		if (OBJECT_MAPPER == null)
			OBJECT_MAPPER = new ObjectMapper();

		return OBJECT_MAPPER;
	}
}
