package com.fon.hotel.service.impl;


import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.ReservationDTO;
import com.fon.hotel.mapper.ReservationMapper;
import com.fon.hotel.repository.ReservationRepository;
import com.fon.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public Page<ReservationDTO> findPage(Pageable pageable) throws HotelServiceException {
        return reservationRepository.findAll(pageable).map(reservationMapper.toDTOFunction());
    }

    @Override
    public Page<ReservationDTO> searchPage(Pageable pageable, String param) throws HotelServiceException {
        return reservationRepository.findAllByParam(pageable, param).map(reservationMapper.toDTOFunction());
    }

    @Override
    public ReservationDTO save(ReservationDTO object) throws HotelServiceException {
        if (reservationRepository.existsById(object.getReservationId()))
            throw new HotelServiceException("Vec postoji rezervacija sa tim id-em");
        return reservationMapper.toDTO(reservationRepository.save(reservationMapper.toDAO(object)));
    }

    @Override
    public ReservationDTO update(ReservationDTO object) throws HotelServiceException {
        if (!reservationRepository.existsById(object.getReservationId()))
            throw new HotelServiceException("Ne postoji rezervacija sa tim id-em");
        return reservationMapper.toDTO(reservationRepository.save(reservationMapper.toDAO(object)));
    }

    @Override
    public void delete(ReservationDTO object) throws HotelServiceException {
        if (!reservationRepository.existsById(object.getReservationId()))
            throw new HotelServiceException("Ne postoji rezervacija sa tim id-em");
        reservationRepository.deleteById(object.getReservationId());
    }

    @Override
    public List<ReservationDTO> getAll() throws HotelServiceException {
        return reservationMapper.toDTO(reservationRepository.findAll());
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) throws HotelServiceException {
        return reservationRepository.findById(id).map(reservationMapper.toDTOFunction());
    }
}
