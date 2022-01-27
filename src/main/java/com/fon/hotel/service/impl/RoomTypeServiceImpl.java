package com.fon.hotel.service.impl;

import com.fon.hotel.dao.RoomType;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.RoomTypeDTO;
import com.fon.hotel.mapper.RoomTypeMapper;
import com.fon.hotel.mapper.config.CycleAvoidingMappingContext;
import com.fon.hotel.repository.RoomTypeRepository;
import com.fon.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeDTO save(RoomTypeDTO object) throws HotelServiceException {
        if (roomTypeRepository.existsByRoomTypeName(object.getRoomTypeName()) || roomTypeRepository.existsById(object.getRoomTypeId()))
            throw new HotelServiceException("Vec postoji tip sobe sa tim nazivom ili Id-em");
        return roomTypeMapper.toDTO(roomTypeRepository.save(roomTypeMapper.toDAO(object,new CycleAvoidingMappingContext())),new CycleAvoidingMappingContext());
    }

    @Override
    public RoomTypeDTO update(RoomTypeDTO object) throws HotelServiceException {
        if (!roomTypeRepository.existsByRoomTypeName(object.getRoomTypeName()) || !roomTypeRepository.existsById(object.getRoomTypeId()))
            throw new HotelServiceException("Vec postoji tip sobe sa tim nazivom ili Id-em");
        return roomTypeMapper.toDTO(roomTypeRepository.save(roomTypeMapper.toDAO(object,new CycleAvoidingMappingContext())),new CycleAvoidingMappingContext());
    }

    @Override
    public void delete(RoomTypeDTO object) throws HotelServiceException {
        if (!roomTypeRepository.existsByRoomTypeName(object.getRoomTypeName()) || !roomTypeRepository.existsById(object.getRoomTypeId()))
            throw new HotelServiceException("Vec postoji tip sobe sa tim nazivom ili Id-em");
        roomTypeRepository.deleteById(object.getRoomTypeId());
    }

    @Override
    public List<RoomTypeDTO> getAll() throws HotelServiceException {
        return roomTypeMapper.toDTO(roomTypeRepository.findAll(),new CycleAvoidingMappingContext());
    }

    @Override
    public Optional<RoomTypeDTO> findById(Long id) throws HotelServiceException {
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        return roomType.map(type -> roomTypeMapper.toDTO(type,new CycleAvoidingMappingContext()));
    }
}
