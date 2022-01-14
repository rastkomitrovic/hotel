package com.fon.hotel.mapper;

import com.fon.hotel.dao.ReservationService;
import com.fon.hotel.dto.ReservationServiceDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationServiceMapper extends GenericMapper<ReservationService, ReservationServiceDTO> {


}
