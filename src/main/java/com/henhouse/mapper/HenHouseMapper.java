package com.henhouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.henhouse.dto.HenHouseDto;
import com.henhouse.model.DateTime;
import com.henhouse.model.HenHouse;

@Mapper
public interface HenHouseMapper {
	HenHouseMapper INSTANCE = Mappers.getMapper(HenHouseMapper.class);

	@Mapping(source = "henHouse", target=".")
	@Mapping(source = "dateTime", target = ".")
	@Mapping(source = "state", target = ".")
	HenHouseDto henHouseToDto(HenHouse henHouse, DateTime dateTime, int state);

	HenHouse dtoToHenHouse(HenHouseDto henHouseDto);

	DateTime dtoToDateTime(HenHouseDto henHouseDto);
}
