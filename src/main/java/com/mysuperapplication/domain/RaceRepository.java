package com.mysuperapplication.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by isaevpd-kde on 12/6/16.
 */


public interface RaceRepository extends CrudRepository<Race, Long> {
    List<Race> findByName(String name);
}