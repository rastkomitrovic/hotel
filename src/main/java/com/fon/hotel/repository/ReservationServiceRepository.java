package com.fon.hotel.repository;

import com.fon.hotel.dao.ReservationService;
import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationServiceRepository extends CrudRepository<ReservationService, ReservationServiceEmbeddedId> {

    @Query("select r from ReservationService r where r.reservationServiceEmbeddedId.reservation.reservationId = :reservationId and r.reservationServiceEmbeddedId.service.serviceId = :serviceId")
    boolean existsByReservationIdAndServiceId(@Param("reservationId") long reservationId, @Param("serviceId") long serviceId);

    @Query("select r from ReservationService r where r.reservationServiceEmbeddedId.reservation.reservationId = :reservationId")
    List<ReservationService> findAllByReservationId(@Param("reservationId") long reservationId);
}
