package com.fon.hotel.mapper;

import com.fon.hotel.dao.Reservation;
import com.fon.hotel.dto.ReservationDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends GenericMapper<Reservation, ReservationDTO> {
}
