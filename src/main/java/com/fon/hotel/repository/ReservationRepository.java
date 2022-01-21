package com.fon.hotel.repository;

import com.fon.hotel.dao.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    /*@Query(value = "select r from Reservation r join fetch r.employee join fetch r.reservationServices join fetch r.room join fetch r.user where concat(r.user.firstName,' ',r.user.lastName) like concat('%',:param,'%') or r.user.username like concat('%',:param,'%')",
    countQuery = "select count r from Reservation r join fetch r.employee join fetch r.reservationServices join fetch r.room join fetch r.user where concat(r.user.firstName,' ',r.user.lastName) like concat('%',:param,'%') or r.user.username like concat('%',:param,'%')")*/
    @Query(value = "select * from reservation inner join users u on reservation.employee_id = u.user_id inner join reservationservice on reservation.reservation_id = reservationservice.reservation_id inner join room on reservation.room_id = room.room_id inner join users uu on reservation.user_id = uu.user_id where concat(uu.first_name,' ',uu.last_name) like concat('%',:param,'%') or uu.username like concat('%',:param,'%')",
            countQuery = "select COUNT(*) from reservation inner join users u on reservation.employee_id = u.user_id inner join reservationservice on reservation.reservation_id = reservationservice.reservation_id inner join room on reservation.room_id = room.room_id inner join users uu on reservation.user_id = uu.user_id  where concat(uu.first_name,' ',uu.last_name) like concat('%',:param,'%') or uu.username like concat('%',:param,'%')",
            nativeQuery = true)
    Page<Reservation> findAllByParam(Pageable pageable, @Param("param") String param);

    /*@Query(value = "Select r from Reservation r join fetch r.employee join fetch r.reservationServices join fetch r.room where r.user.username=:username",
            countQuery = "select count r from Reservation r join fetch r.employee join fetch r.reservationServices join fetch  r.room where r.user.username=:username")*/
    @Query(value = "Select * from reservation r inner join users u on r.employee_id = u.user_id inner join reservationservice on reservationservice.reservation_id = r.reservation_id inner join room on r.room_id = room.room_id inner join users uu on r.user_id = uu.user_id where uu.username=:username",
    countQuery = "Select COUNT(*) from reservation r inner join users u on r.employee_id = u.user_id inner join reservationservice on reservationservice.reservation_id = r.reservation_id inner join room on r.room_id = room.room_id inner join users uu on r.user_id = uu.user_id where uu.username=:username",
    nativeQuery = true)
    Page<Reservation> findAllForUser(Pageable pageable, @Param("username") String username);
}
