package com.fon.hotel.mapper;

import com.fon.hotel.dao.Service;
import com.fon.hotel.dto.ServiceDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends GenericMapper<Service, ServiceDTO> {
}
