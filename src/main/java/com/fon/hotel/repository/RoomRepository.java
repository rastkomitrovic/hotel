package com.fon.hotel.repository;

import com.fon.hotel.dao.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    boolean existsByRoomNumberAndFloor(int roomNumber, int floor);

    @Query("Select r from Room r where r.roomNumber=:param or r.floor=:param or r.roomType.roomTypeName like concat('%',:param,'%')")
    Page<Room> findAllByParam(Pageable pageable, @Param("param") String param);

    @Query(value = "select ro.*, rt.* from room ro join (select rr.room_id as room_id from reservationroom rr join reservation re on rr.reservation_id = re.reservation_id where re.start_date > :endDate || re.end_date< :startDate) rero ON ro.room_id = rero.room_id JOIN roomtype rt on ro.room_type_id = rt.room_type_id",nativeQuery = true)
    List<Room> findAllAvailable(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "select ro.*, rt.* from room ro join (select rr.room_id as room_id from reservationroom rr join reservation re on rr.reservation_id = re.reservation_id where re.start_date > :endDate || re.end_date< :startDate and re.reservation_id != :reservationId) rero ON ro.room_id = rero.room_id JOIN roomtype rt on ro.room_type_id = rt.room_type_id",nativeQuery = true)
    List<Room> findAllAvailableExcludingReservation(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("reservationId")Long reservationId);
}
