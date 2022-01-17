package com.fon.hotel.service;

import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.generic.GenericPagingAndSortingService;

import java.util.Optional;

public interface UserService extends GenericPagingAndSortingService<UserDTO, Long> {

    Optional<UserDTO> findByUsername(String username) throws HotelServiceException;

    void updatePassword(String username,String password) throws HotelServiceException;
}
