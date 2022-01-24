package com.fon.hotel.repository;

import com.fon.hotel.dao.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {
    Page<Service> findAllByServiceNameContaining(Pageable pageable, String param);
    boolean existsByServiceName(String serviceName);
    Optional<Service> findByServiceName(String serviceName);
}
