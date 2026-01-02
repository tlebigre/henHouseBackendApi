package com.hen.house.mapper;

import com.hen.house.dto.LastOpeningDto;
import com.hen.house.model.LastOpening;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jsr330")
public interface LastOpeningMapper {
	LastOpeningDto lastOpeningToDto(LastOpening lastOpening);

	LastOpening dtoToLastOpening(LastOpeningDto lastOpeningDto);
}
