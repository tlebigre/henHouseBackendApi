package com.hen.house.repository;

import com.hen.house.model.Identifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonListRepositoryImpl<T extends Identifier> implements JsonListRepository<T> {

	private final Class<T> type;
	private final String fileName;

	public JsonListRepositoryImpl(Class<T> type) {
		this.type = type;
		fileName = type.getSimpleName();
		new File(fileName+".json");
	}

	@Override
	public Optional<T> get(int id) {
		return getAll().stream().filter(a -> id == a.getId()).findAny();
	}

	@Override
	public List<T> getAll() {
		return JsonRepositoryUtil.get(fileName,
				ObjectMapperInstance.getInstance().getTypeFactory().constructCollectionType(List.class, type),
                ArrayList::new, (List<T> t) -> t);
	}

	@Override
	public void save(T object) {
		List<T> all = getAll();
		int id = object.getId();
		if (-1 == id)
			object.setId(getId(all));
		else
			all.removeIf(a -> id == a.getId());

		all.add(object);
		JsonRepositoryUtil.save(all, fileName);
	}

	@Override
	public void remove(int id) {
		List<T> all = getAll();
		all.removeIf(a -> id == a.getId());
		JsonRepositoryUtil.save(all, fileName);
	}

	private int getId(List<T> all) {
		return all.stream().mapToInt(Identifier::getId).map(i -> i + 1).max().orElse(0);
	}
}
