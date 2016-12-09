package com.mysuperapplication.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by isaevpd-kde on 12/6/16.
 */

public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findByFirstName(@Param("name") String firstName);
}