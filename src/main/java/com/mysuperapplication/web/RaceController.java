package com.mysuperapplication.web;

import com.mysuperapplication.domain.Race;
import com.mysuperapplication.domain.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by isaevpd-kde on 12/6/16.
 */

@RestController
@RequestMapping("/races")
public class RaceController {
    @Autowired
    private RaceRepository repository;
    private class RaceParseException extends Exception {
        public RaceParseException() {
            super();
        }
    }

    private Map<String, Object> validateRaceData(Map<String, String> raceData) throws RaceParseException {
        String name;
        String date;
        Date dateFormatted;
        Map<String, Object> fields = new HashMap<>();
        try {
            name = raceData.getOrDefault("name", "");
            date = raceData.get("date");
            if (name.equals("")) {
                throw new RaceParseException();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatted = format.parse(date);
        }
        catch (ParseException e) {
            throw new RaceParseException();
        }

        fields.put("name", name);
        fields.put("date", dateFormatted);
        return fields;
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Object>
    getAllRaces() {
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Race> findRaceById(@PathVariable("id") Long raceId) {
        Race race = repository.findOne(raceId);;
        if (race == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(race, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Race>
    createRace(@RequestBody final Map<String, String> newRace) {
        Map<String, Object> fields;
        try {
            fields = validateRaceData(newRace);
        }
        catch (RaceParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Race createdRace = new Race(
                (String) fields.get("name"),
                (Date) fields.get("date")
        );
        return new ResponseEntity<>(repository.save(createdRace), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRaceById(@PathVariable("id") Long raceId) {
        if (repository.findOne(raceId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(raceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateRace (@PathVariable("id") Long raceId,
                                              @RequestBody final Map<String, String> updatedRace) {
        Race race = repository.findOne(raceId);
        Map<String, Object> fields;
        try {
            fields = validateRaceData(updatedRace);
        }
        catch (RaceParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        race.setName((String) fields.get("name"));
        race.setDate((Date) fields.get("date"));
        return new ResponseEntity<>(repository.save(race), HttpStatus.OK);
    }



}
