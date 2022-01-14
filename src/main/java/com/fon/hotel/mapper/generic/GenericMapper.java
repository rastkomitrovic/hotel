package com.fon.hotel.mapper.generic;

import java.util.List;
import java.util.function.Function;

public interface GenericMapper<T,R> {

    R toDTO(T object);

    T toDAO(R object);

    List<R> toDTO(Iterable<T> list);

    default Function<T,R> toDTOFunction(){
        return this::toDTO;
    }
}
