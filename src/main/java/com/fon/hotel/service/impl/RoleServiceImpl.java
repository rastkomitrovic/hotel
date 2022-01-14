package com.fon.hotel.service.impl;

import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.mapper.RoleMapper;
import com.fon.hotel.repository.RoleRepository;
import com.fon.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO save(RoleDTO object) throws HotelServiceException {
        if(roleRepository.existsByRoleName(object.getRoleName()) || roleRepository.existsById(object.getRoleId()))
            throw new HotelServiceException("Vec postoji rola sa tim nazivom ili Id-em");
        return roleMapper.toDTO(roleRepository.save(roleMapper.toDAO(object)));
    }

    @Override
    public RoleDTO update(RoleDTO object) throws HotelServiceException {
        if(!roleRepository.existsByRoleName(object.getRoleName()) || !roleRepository.existsById(object.getRoleId()))
            throw new HotelServiceException("Ne postoji rola sa tim nazivom ili Id-em");
        return roleMapper.toDTO(roleRepository.save(roleMapper.toDAO(object)));
    }

    @Override
    public void delete(RoleDTO object) throws HotelServiceException {
        if(!roleRepository.existsByRoleName(object.getRoleName()) || !roleRepository.existsById(object.getRoleId()))
            throw new HotelServiceException("Ne postoji rola sa tim nazivom ili Id-em");
        roleRepository.deleteById(object.getRoleId());
    }

    @Override
    public List<RoleDTO> getAll() throws HotelServiceException {
        return roleMapper.toDTO(roleRepository.findAll());
    }

    @Override
    public Optional<RoleDTO> findById(Long id) throws HotelServiceException {
        return roleRepository.findById(id).map(roleMapper.toDTOFunction());
    }
}
