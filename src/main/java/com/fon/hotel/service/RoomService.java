package com.fon.hotel.service;

import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.generic.GenericPagingAndSortingService;

import java.util.Date;
import java.util.List;

public interface RoomService extends GenericPagingAndSortingService<RoomDTO, Long> {
    List<RoomDTO> getAvailableRoomsForPeriod(Date startDate, Date endDate) throws HotelServiceException;
    List<RoomDTO> findAllAvailableExcludingReservation(Date startDate,Date endDate, Long reservationId) throws HotelServiceException;
}
