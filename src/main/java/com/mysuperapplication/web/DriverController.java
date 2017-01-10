package com.mysuperapplication.web;

import com.mysuperapplication.domain.Driver;
import com.mysuperapplication.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by isaevpd-kde on 12/6/16.
 */

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverRepository repository;
    private List<String> DRIVER_FIELDS = Arrays.asList("firstName", "lastName", "country", "team");

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Object>
    getAllDrivers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Driver> findDriverById(@PathVariable("id") Long driverId) {
        Driver driver = repository.findOne(driverId);;
        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Driver>
    createDriver(@RequestBody final Map<String, String> newDriver) {
        String field;
        for (String s: DRIVER_FIELDS) {
            field = newDriver.get(s);
            if (field == null || field.equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        Driver createdDriver = new Driver(
                newDriver.get("firstName"),
                newDriver.get("lastName"),
                newDriver.get("country"),
                Long.parseLong(newDriver.get("team"))
        );
        return new ResponseEntity<>(repository.save(createdDriver), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDriverById(@PathVariable("id") Long driverId) {
        if (repository.findOne(driverId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(driverId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateDriver (@PathVariable("id") Long driverId,
                                              @RequestBody final Map<String, String> newDriver) {
        Driver driver = repository.findOne(driverId);
        String field;
        for (String s : DRIVER_FIELDS) {
            field = newDriver.get(s);
            if (field == null || field.equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        driver.setFirstName(newDriver.get("firstName"));
        driver.setLastName(newDriver.get("lastName"));
        driver.setCountry(newDriver.get("country"));
        driver.setTeam(Long.parseLong(newDriver.get("team")));
        return new ResponseEntity<>(repository.save(driver), HttpStatus.OK);
    }



}
