package com.hen.house.repository;

import com.fasterxml.jackson.databind.JavaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.function.Supplier;

public class JsonRepositoryUtil {

	private JsonRepositoryUtil() {}

	private static final Logger LOGGER = LogManager.getLogger(JsonRepositoryUtil.class);

	public static void save(Object object, String fileName) {
		if (fileName == null || fileName.isBlank())
			return;
		try {
			ObjectMapperInstance.getInstance().writeValue(Paths.get(fileName).toFile(), object);
		} catch (IOException e) {
			LOGGER.warn("Bad mapping : object to file \n" + e.getMessage());
		}
	}

	public static <T, V, R> R get(String fileName, JavaType valueType, Supplier<R> empty, Function<V, R> value) {
		if (fileName == null || fileName.isBlank())
			return empty.get();

		try {
			File file = Paths.get(fileName).toFile();
			if (file.length() > 0)
				return value.apply(ObjectMapperInstance.getInstance().readValue(file, valueType));
		} catch (IOException e) {
			LOGGER.warn("Bad mapping : file to object \n" + e.getMessage());
		}
		return empty.get();
	}
}
