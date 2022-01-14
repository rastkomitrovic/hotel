package com.fon.hotel.service;

import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import com.fon.hotel.dto.ReservationServiceDTO;
import com.fon.hotel.service.generic.GenericCrudService;

import java.util.List;

public interface ReservationServiceService extends GenericCrudService<ReservationServiceDTO, ReservationServiceEmbeddedId> {

    List<ReservationServiceDTO> findAllByReservationId(long reservationId);
}
