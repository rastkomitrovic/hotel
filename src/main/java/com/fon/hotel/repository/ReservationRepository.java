package com.fon.hotel.repository;

import com.fon.hotel.dao.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    @Query(value = "select r from Reservation r where concat(r.user.firstName,' ',r.user.lastName) like concat('%',:param,'%') or r.user.username like concat('%',:param,'%') or r.startDate like concat('%',:param,'%') or r.endDate like concat('%',:param,'%')")
    Page<Reservation> findAllByParam(Pageable pageable, String param);
}
