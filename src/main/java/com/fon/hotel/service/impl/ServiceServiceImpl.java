package com.fon.hotel.service.impl;

import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.ServiceDTO;
import com.fon.hotel.mapper.ServiceMapper;
import com.fon.hotel.repository.ServiceRepository;
import com.fon.hotel.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public Page<ServiceDTO> findPage(Pageable pageable) throws HotelServiceException {
        return serviceRepository.findAll(pageable).map(serviceMapper.toDTOFunction());
    }

    @Override
    public Page<ServiceDTO> searchPage(Pageable pageable, String param) throws HotelServiceException {
        return serviceRepository.findAllByServiceNameContaining(pageable, param).map(serviceMapper.toDTOFunction());
    }

    @Override
    public ServiceDTO save(ServiceDTO object) throws HotelServiceException {
        if (serviceRepository.existsByServiceName(object.getServiceName()) || serviceRepository.existsById(object.getServiceId()))
            throw new HotelServiceException("Vec postoji usluga sa unetim nazivom ili Id-em");
        return serviceMapper.toDTO(serviceRepository.save(serviceMapper.toDAO(object)));
    }

    @Override
    public ServiceDTO update(ServiceDTO object) throws HotelServiceException {
        if (!serviceRepository.existsByServiceName(object.getServiceName()) || serviceRepository.existsById(object.getServiceId()))
            throw new HotelServiceException("Ne postoji usluga sa unetim nazivom ili Id-em");
        return serviceMapper.toDTO(serviceRepository.save(serviceMapper.toDAO(object)));
    }

    @Override
    public void delete(ServiceDTO object) throws HotelServiceException {
        if (!serviceRepository.existsByServiceName(object.getServiceName()) || !serviceRepository.existsById(object.getServiceId()))
            throw new HotelServiceException("Ne postoji usluga sa unetim nazivom ili Id-em");
        serviceRepository.deleteById(object.getServiceId());
    }

    @Override
    public List<ServiceDTO> getAll() throws HotelServiceException {
        return serviceMapper.toDTO(serviceRepository.findAll());
    }

    @Override
    public Optional<ServiceDTO> findById(Long id) throws HotelServiceException{
        return serviceRepository.findById(id).map(serviceMapper.toDTOFunction());
    }

    @Override
    public Optional<ServiceDTO> findByName(String name) {
        return Optional.ofNullable(serviceMapper.toDTO(serviceRepository.findByServiceName(name).get()));
    }
}
