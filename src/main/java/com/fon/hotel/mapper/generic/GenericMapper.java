package com.fon.hotel.mapper.generic;

import com.fon.hotel.mapper.config.CycleAvoidingMappingContext;
import org.mapstruct.Context;

import java.util.List;
import java.util.function.Function;

public interface GenericMapper<T,R> {

    R toDTO(T object, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    T toDAO(R object, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<R> toDTO(Iterable<T> list, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    default Function<T,R> toDTOFunction(@Context CycleAvoidingMappingContext cycleAvoidingMappingContext){
        return new Function<T, R>() {
            @Override
            public R apply(T t) {
                return toDTO(t,cycleAvoidingMappingContext);
            }
        };
    }
}
