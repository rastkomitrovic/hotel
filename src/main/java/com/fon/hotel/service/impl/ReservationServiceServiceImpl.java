package com.fon.hotel.service.impl;

import com.fon.hotel.dao.ReservationServiceEmbeddedId;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.ReservationServiceDTO;
import com.fon.hotel.mapper.ReservationServiceMapper;
import com.fon.hotel.repository.ReservationRepository;
import com.fon.hotel.repository.ReservationServiceRepository;
import com.fon.hotel.service.ReservationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceServiceImpl implements ReservationServiceService {

    @Autowired
    private ReservationServiceRepository reservationServiceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceMapper reservationServiceMapper;

    @Override
    public List<ReservationServiceDTO> findAllByReservationId(long reservationId) {
        return reservationServiceMapper.toDTO(reservationServiceRepository.findAllByReservationId(reservationId));
    }

    @Override
    public ReservationServiceDTO save(ReservationServiceDTO object) throws HotelServiceException {
        if (reservationServiceRepository.existsByReservationIdAndServiceId(object.getReservationServiceEmbeddedId().getReservation().getReservationId(), object.getReservationServiceEmbeddedId().getService().getServiceId()))
            throw new HotelServiceException("Vec postoji ova usluga za ovu rezervaciju");
        if (reservationRepository.existsById(object.getReservationServiceEmbeddedId().getReservation().getReservationId()))
            return reservationServiceMapper.toDTO(reservationServiceRepository.save(reservationServiceMapper.toDAO(object)));
        else
            throw new HotelServiceException("Ne postoji prosledjena rezervacija");
    }

    @Override
    public ReservationServiceDTO update(ReservationServiceDTO object) throws HotelServiceException {
        if (!reservationServiceRepository.existsByReservationIdAndServiceId(object.getReservationServiceEmbeddedId().getReservation().getReservationId(), object.getReservationServiceEmbeddedId().getService().getServiceId()))
            throw new HotelServiceException("Ne postoji izabrana usluga za izabranu rezervaciju");
        return reservationServiceMapper.toDTO(reservationServiceRepository.save(reservationServiceMapper.toDAO(object)));
    }

    @Override
    public void delete(ReservationServiceDTO object) throws HotelServiceException {
        if (!reservationServiceRepository.existsByReservationIdAndServiceId(object.getReservationServiceEmbeddedId().getReservation().getReservationId(), object.getReservationServiceEmbeddedId().getService().getServiceId()))
            throw new HotelServiceException("Ne postoji izabrana usluga za izabranu rezervaciju");
        reservationServiceRepository.delete(reservationServiceMapper.toDAO(object));
    }

    @Override
    public List<ReservationServiceDTO> getAll() throws HotelServiceException {
        return reservationServiceMapper.toDTO(reservationServiceRepository.findAll());
    }

    @Override
    public Optional<ReservationServiceDTO> findById(ReservationServiceEmbeddedId id) throws HotelServiceException {
        return reservationServiceRepository.findById(id).map(reservationServiceMapper.toDTOFunction());
    }
}
