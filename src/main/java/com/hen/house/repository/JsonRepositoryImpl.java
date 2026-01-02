package com.hen.house.repository;

import java.io.File;
import java.util.Optional;

public class JsonRepositoryImpl<T> implements BaseRepository<T> {

	private final Class<T> type;
	private final String fileName;

	public JsonRepositoryImpl(Class<T> type) {
		this.type = type;
		fileName = type.getSimpleName();
		new File(fileName+".json");
	}

	@Override
	public Optional<T> get() {
		return JsonRepositoryUtil.get(fileName, ObjectMapperInstance.getInstance().getTypeFactory().constructType(type),
				Optional::empty, (T t) -> Optional.of(t));
	}

	@Override
	public void save(T object) {
		JsonRepositoryUtil.save(object, fileName);
	}

}
