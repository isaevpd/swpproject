package com.mysuperapplication.web;

import com.mysuperapplication.domain.Driver;
import com.mysuperapplication.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by isaevpd-kde on 12/6/16.
 */

@Controller
public class DriverController {
    @Autowired
    private DriverRepository repository;

    @RequestMapping(value="/drivers")
    public @ResponseBody
    List<Driver> driverListRest() {
        return (List<Driver>) repository.findAll();
    }

    @RequestMapping(value="/drivers", method=RequestMethod.POST)
    public @ResponseBody
    List<Driver> driverListRest() {
        return (List<Driver>) repository.findAll();
    }


    @RequestMapping(value="/drivers/{id}", method=RequestMethod.PATCH)
    public @ResponseBody Driver updateDriver (@PathVariable("id") Long driverId,
                                              @RequestBody final Map<String, String> newDriver) {
        Driver driver = repository.findOne(driverId);
//        for (String s : newDriver.keySet()) {
//            System.out.println(driverData.get(s));
//        }
//        System.out.println(newDriver);
        driver.setLastName(newDriver.get("lastName"));
        return repository.save(driver);
    }

    @RequestMapping(value="/drivers/{id}", method=RequestMethod.GET)
    public @ResponseBody Driver findDriverById(@PathVariable("id") Long driverId) {
        return repository.findOne(driverId);
    }


}
