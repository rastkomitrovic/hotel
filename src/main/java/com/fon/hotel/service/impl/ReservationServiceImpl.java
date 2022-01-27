package com.fon.hotel.service.impl;


import com.fon.hotel.dao.Reservation;
import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import com.fon.hotel.dto.ReservationServiceDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.ReservationDTO;
import com.fon.hotel.mapper.ReservationMapper;
import com.fon.hotel.mapper.ReservationServiceEmbeddedIdMapper;
import com.fon.hotel.mapper.ReservationServiceMapper;
import com.fon.hotel.repository.ReservationRepository;
import com.fon.hotel.repository.ReservationServiceRepository;
import com.fon.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceRepository reservationServiceRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ReservationServiceMapper reservationServiceMapper;

    @Autowired
    private ReservationServiceEmbeddedIdMapper reservationServiceEmbeddedIdMapper;

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
        Reservation reservation =reservationRepository.save(reservationMapper.toDAO(object));
        List<com.fon.hotel.dao.ReservationService> list = new LinkedList<>();
        for(ReservationServiceDTO reservationServiceDTO:object.getReservationServices()){
            com.fon.hotel.dao.ReservationService rs = reservationServiceMapper.toDAO(reservationServiceDTO);
            ReservationServiceEmbeddedId rsemId = reservationServiceEmbeddedIdMapper.toDAO(reservationServiceDTO.getReservationServiceEmbeddedIdDTO());
            rs.setReservationServiceEmbeddedId(rsemId);
            rs.getReservationServiceEmbeddedId().setReservation(reservation);
            list.add(rs);
        }
        if(!list.isEmpty())
            reservationServiceRepository.saveAll(list);
        return reservationMapper.toDTO(reservation);
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

    @Override
    public Page<ReservationDTO> findAllForUser(Pageable pageable, String username) {
        return reservationRepository.findAllForUser(pageable, username).map(reservationMapper.toDTOFunction());
    }
}
