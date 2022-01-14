package com.fon.hotel.repository;

import com.fon.hotel.dao.ReservationService;
import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationServiceRepository extends CrudRepository<ReservationService, ReservationServiceEmbeddedId> {

    @Query("select r from ReservationService r where r.reservationServiceEmbeddedId.reservation.reservationId = :reservationId and r.reservationServiceEmbeddedId.service.serviceId = :serviceId")
    boolean existsByReservationIdAndServiceId(long reservationId, long serviceId);

    @Query("select r from ReservationService r where r.reservationServiceEmbeddedId.reservation.reservationId = :reservationId")
    List<ReservationService> findAllByReservationId(long reservationId);
}
