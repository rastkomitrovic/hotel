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

    @Query(value = "SELECT ro.*, rt.*\n" +
            "FROM room ro INNER join roomtype rt on ro.room_type_id = rt.room_type_id left outer join (\n" +
            "    select rr.room_id as room_id \n" +
            "    from reservationroom rr join \n" +
            "    reservation re on rr.reservation_id = re.reservation_id\n" +
            "    where re.start_date <= :startDate and re.end_date >= :endDate\n" +
            "    or re.start_date <= :startDate and re.end_date >= :endDate) rero\n" +
            "ON ro.room_id = rero.room_id\n" +
            "where rero.room_id is null",nativeQuery = true)
    List<Room> findAllAvailable(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
