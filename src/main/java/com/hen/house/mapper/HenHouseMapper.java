package com.hen.house.mapper;

import com.hen.house.dto.HenHouseDto;
import com.hen.house.model.DateTime;
import com.hen.house.model.HenHouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface HenHouseMapper {
	@Mapping(source = "henHouse", target=".")
	@Mapping(source = "dateTime", target = ".")
	@Mapping(source = "state", target = ".")
	HenHouseDto henHouseToDto(HenHouse henHouse, DateTime dateTime, int state);

	HenHouse dtoToHenHouse(HenHouseDto henHouseDto);

	DateTime dtoToDateTime(HenHouseDto henHouseDto);
}
