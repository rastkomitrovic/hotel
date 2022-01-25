package com.fon.hotel.service;

import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.service.generic.GenericPagingAndSortingService;

import java.util.Date;
import java.util.List;

public interface RoomService extends GenericPagingAndSortingService<RoomDTO, Long> {
    List<RoomDTO> getAvailableRoomsForPeriod(Date startDate, Date endDate);
}
