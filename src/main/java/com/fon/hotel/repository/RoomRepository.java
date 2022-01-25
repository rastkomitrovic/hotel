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

    @Query(value = "SELECT * from room inner join roomtype r on room.room_type_id = r.room_type_id where room.room_id in (Select room_id from reservationroom inner join reservation r2 on reservationroom.reservation_id = r2.reservation_id where :startDate not between r2.start_date and r2.end_date and :endDate not between r2.start_date and r2.end_date)",nativeQuery = true)
    List<Room> findAllAvailable(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
