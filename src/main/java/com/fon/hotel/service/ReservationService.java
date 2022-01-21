package com.fon.hotel.service;

import com.fon.hotel.dto.ReservationDTO;
import com.fon.hotel.service.generic.GenericPagingAndSortingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ReservationService extends GenericPagingAndSortingService<ReservationDTO, Long> {

    Page<ReservationDTO> findAllForUser(Pageable pageable, @Param("username") String username);
}
