package com.fon.hotel.mapper;

import com.fon.hotel.dao.RoomType;
import com.fon.hotel.dto.RoomTypeDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper extends GenericMapper<RoomType, RoomTypeDTO> {
}
