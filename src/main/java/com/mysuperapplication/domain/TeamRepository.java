package com.mysuperapplication.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by isaevpd-kde on 12/6/16.
 */


public interface TeamRepository extends CrudRepository<Team, Long> {
//    List<Team> findByName(String name);
}