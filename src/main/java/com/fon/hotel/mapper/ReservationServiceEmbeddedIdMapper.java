package com.fon.hotel.mapper;

import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import com.fon.hotel.dto.ReservationServiceEmbeddedIdDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationServiceEmbeddedIdMapper extends GenericMapper<ReservationServiceEmbeddedId, ReservationServiceEmbeddedIdDTO> {
}
