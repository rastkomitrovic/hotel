package com.fon.hotel.repository;

import com.fon.hotel.dao.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {

    boolean existsByRoomTypeName(String roomTypeName);
}
