package com.fon.hotel.mapper;

import com.fon.hotel.dao.Room;
import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends GenericMapper<Room, RoomDTO> {
}
