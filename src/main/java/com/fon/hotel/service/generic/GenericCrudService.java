package com.fon.hotel.service.generic;

import com.fon.hotel.exception.HotelServiceException;

import java.util.List;
import java.util.Optional;

public interface GenericCrudService<T,R> {
    T save(T object) throws HotelServiceException;
    T update(T object) throws HotelServiceException;
    void delete(T object) throws HotelServiceException;
    List<T> getAll() throws HotelServiceException;
    Optional<T> findById(R id) throws HotelServiceException;
}
