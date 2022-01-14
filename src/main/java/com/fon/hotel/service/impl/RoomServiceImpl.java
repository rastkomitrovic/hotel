package com.fon.hotel.service.impl;

import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.mapper.RoomMapper;
import com.fon.hotel.repository.RoomRepository;
import com.fon.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Page<RoomDTO> findPage(Pageable pageable) throws HotelServiceException {
        return roomRepository.findAll(pageable).map(roomMapper.toDTOFunction());
    }

    @Override
    public Page<RoomDTO> searchPage(Pageable pageable, String param) throws HotelServiceException {
        return roomRepository.findAllByParam(pageable, param).map(roomMapper.toDTOFunction());
    }

    @Override
    public RoomDTO save(RoomDTO object) throws HotelServiceException {
        if (roomRepository.existsByRoomNumberAndFloor(object.getRoomNumber(), object.getFloor()) || roomRepository.existsById(object.getRoomId()))
            throw new HotelServiceException("Vec postoji soba sa tim brojem na tom spratu ili sa tim Id-em");
        return roomMapper.toDTO(roomRepository.save(roomMapper.toDAO(object)));
    }

    @Override
    public RoomDTO update(RoomDTO object) throws HotelServiceException {
        if (!roomRepository.existsByRoomNumberAndFloor(object.getRoomNumber(), object.getFloor()) || !roomRepository.existsById(object.getRoomId()))
            throw new HotelServiceException("Ne postoji soba sa tim brojem na tom spratu ili sa tim Id-em");
        return roomMapper.toDTO(roomRepository.save(roomMapper.toDAO(object)));
    }

    @Override
    public void delete(RoomDTO object) throws HotelServiceException {
        if (!roomRepository.existsByRoomNumberAndFloor(object.getRoomNumber(), object.getFloor()) || !roomRepository.existsById(object.getRoomId()))
            throw new HotelServiceException("Ne postoji soba sa tim brojem na tom spratu ili sa tim Id-em");
        roomRepository.deleteById(object.getRoomId());
    }

    @Override
    public List<RoomDTO> getAll() throws HotelServiceException {
        return roomMapper.toDTO(roomRepository.findAll());
    }

    @Override
    public Optional<RoomDTO> findById(Long id) throws HotelServiceException {
        return roomRepository.findById(id).map(roomMapper.toDTOFunction());
    }
}
