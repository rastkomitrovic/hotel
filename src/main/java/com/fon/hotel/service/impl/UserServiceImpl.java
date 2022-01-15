package com.fon.hotel.service.impl;

import com.fon.hotel.dao.User;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.mapper.UserMapper;
import com.fon.hotel.repository.UserRepository;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> findPage(Pageable pageable) throws HotelServiceException {
        return userRepository.findAll(pageable).map(userMapper.toDTOFunction());
    }

    @Override
    public Page<UserDTO> searchPage(Pageable pageable, String param) throws HotelServiceException {
        return userRepository.findAllByParam(pageable, param).map(userMapper.toDTOFunction());
    }

    @Override
    public UserDTO save(UserDTO object) throws HotelServiceException {
        if (userRepository.existsByUsername(object.getUsername()))
            throw new HotelServiceException("Vec postoji korisnik sa unetim korisnickim imenom");
        if(userRepository.existsById(object.getUserId()))
            throw new HotelServiceException("Vec postoji korisnik sa unetim Id-em");
        if(userRepository.existsByPassportNumber(object.getPassportNumber()))
            throw new HotelServiceException("Vec postoji korisnik sa unetim brojem pasosa");
        if(userRepository.existsByEmail(object.getEmail()))
            throw new HotelServiceException("Vec postoji korisnik sa unetim emailom");
        if(userRepository.existsByPhoneNumber(object.getPhoneNumber()))
            throw new HotelServiceException("Vec postoji korisnik sa unetim brojem telefona");
        return userMapper.toDTO(userRepository.save(userMapper.toDAO(object)));
    }

    @Override
    public UserDTO update(UserDTO object) throws HotelServiceException {
        if (!userRepository.existsByUsername(object.getUsername()) || !userRepository.existsById(object.getUserId()))
            throw new HotelServiceException("Ne postoji korisnik sa unetim korisnickim imenom ili Id-em");
        return userMapper.toDTO(userRepository.save(userMapper.toDAO(object)));
    }

    @Override
    public void delete(UserDTO object) throws HotelServiceException {
        if (!userRepository.existsByUsername(object.getUsername()) || !userRepository.existsById(object.getUserId()))
            throw new HotelServiceException("Ne postoji korisnik sa unetim korisnickim imenom ili Id-em");
        userRepository.deleteById(object.getUserId());
    }

    @Override
    public List<UserDTO> getAll() throws HotelServiceException {
        return userMapper.toDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> findById(Long id) throws HotelServiceException {
        return userRepository.findById(id).map(userMapper.toDTOFunction());
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) throws HotelServiceException{
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> userMapper.toDTO(value));
    }
}

