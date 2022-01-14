package com.fon.hotel.repository;

import com.fon.hotel.dao.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    boolean existsByRoleName(String roleName);
}
