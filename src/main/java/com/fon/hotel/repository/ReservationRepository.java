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
    @Query(value = "select * from reservation inner join users u on reservation.employee_id = u.user_id left join reservationservice on reservation.reservation_id = reservationservice.reservation_id left outer join service s on reservationservice.service_id = s.service_id inner join reservationroom r on reservation.reservation_id = r.reservation_id inner join room on r.room_id = room.room_id inner join users uu on reservation.user_id = uu.user_id where concat(uu.first_name,' ',uu.last_name) like concat('%',:param,'%') or uu.username like concat('%',:param,'%')",
            countQuery = "select COUNT(*) from reservation inner join users u on reservation.employee_id = u.user_id left join reservationservice on reservation.reservation_id = reservationservice.reservation_id left outer join service s on reservationservice.service_id = s.service_id inner join reservationroom r on reservation.reservation_id = r.reservation_id inner join room on r.room_id = room.room_id inner join users uu on reservation.user_id = uu.user_id where concat(uu.first_name,' ',uu.last_name) like concat('%',:param,'%') or uu.username like concat('%',:param,'%')",
            nativeQuery = true)
    Page<Reservation> findAllByParam(Pageable pageable, @Param("param") String param);


    //@Query(value = "Select * from reservation r inner join users u on r.employee_id = u.user_id left join reservationservice rs on rs.reservation_id = r.reservation_id left outer join service s on rs.service_id = s.service_id inner join reservationroom rr on r.reservation_id =rr.reservation_id inner join room ro on rr.room_id = ro.room_id  inner join roomtype rt on rt.room_type_id = ro.room_type_id inner join users uu on r.user_id = uu.user_id where uu.username=:username",
    //countQuery = "Select count(*) from reservation inner join users u on reservation.employee_id = u.user_id where u.username=:username"/*"Select COUNT(*) from reservation r inner join users u on r.employee_id = u.user_id left join reservationservice rs on rs.reservation_id = r.reservation_id left outer join service s on rs.service_id = s.service_id inner join reservationroom rr on r.reservation_id =rr.reservation_id inner join room ro on rr.room_id = ro.room_id inner join roomtype rt on rt.room_type_id = ro.room_type_id inner join users uu on r.user_id = uu.user_id where uu.username=:username"*/,
    //nativeQuery = true)

    @Query(value = "select r from Reservation r join r.user join r.reservationServices join  r.employee join r.rooms where r.user.username=:username",
    countQuery = "select count (r) from Reservation r join  r.user where r.user.username=:username")
    Page<Reservation> findAllForUser(Pageable pageable, @Param("username") String username);


}
