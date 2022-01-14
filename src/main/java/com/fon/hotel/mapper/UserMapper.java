package com.fon.hotel.mapper;


import com.fon.hotel.dao.User;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.mapper.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {

}
