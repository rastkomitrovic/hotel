package com.fon.hotel.service.generic;

import com.fon.hotel.exception.HotelServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericPagingAndSortingService<T,R> extends GenericCrudService<T,R> {
    Page<T> findPage(Pageable pageable) throws HotelServiceException;
    Page<T> searchPage(Pageable pageable, String param) throws HotelServiceException;
}
