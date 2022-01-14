package com.fon.hotel.repository;

import com.fon.hotel.dao.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    boolean existsByRoomNumberAndFloor(int roomNumber, int floor);

    @Query("Select r from Room r where r.roomNumber=:param or r.floor=:param or r.roomType.roomTypeName like concat('%',:param,'%')")
    Page<Room> findAllByParam(Pageable pageable, String param);
}
