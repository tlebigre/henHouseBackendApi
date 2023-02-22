package com.henhouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.henhouse.dto.LastOpeningDto;
import com.henhouse.model.LastOpening;

@Mapper
public interface LastOpeningMapper {
	LastOpeningMapper INSTANCE = Mappers.getMapper(LastOpeningMapper.class);

	LastOpeningDto lastOpeningToDto(LastOpening lastOpening);

	LastOpening dtoToLastOpening(LastOpeningDto lastOpeningDto);
}
