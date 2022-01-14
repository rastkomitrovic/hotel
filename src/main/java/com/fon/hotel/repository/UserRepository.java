package com.fon.hotel.repository;

import com.fon.hotel.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    boolean existsByUsername(String username);

    @Query("select u from User u where u.username like CONCAT('%',:param ,'%')  or concat(u.username, ' ', u.lastName) like CONCAT('%',:param ,'%') ")
    Page<User> findAllByParam(Pageable pageable, String param);

    @Query("Select u from User u where u.username=:username")
    Optional<User> findByUsername(String username);
}
