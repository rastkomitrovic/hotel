package com.fon.hotel.mapper;

import com.fon.hotel.dao.Role;
import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDTO> {
}
