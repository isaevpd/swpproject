package com.mysuperapplication.web;

import com.mysuperapplication.domain.Team;
import com.mysuperapplication.domain.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by isaevpd-kde on 12/6/16.
 */

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamRepository repository;
    private List<String> TEAM_FIELDS = Arrays.asList("name", "car");

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Object>
    getAllTeams() {
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Team> findTeamById(@PathVariable("id") Long teamId) {
        Team team = repository.findOne(teamId);;
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Team>
    createTeam(@RequestBody final Map<String, String> newTeam) {
        String field;
        for (String s: TEAM_FIELDS) {
            field = newTeam.get(s);
            if (field == null || field.equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        Team createdTeam = new Team(
                newTeam.get("name"),
                newTeam.get("car")
        );
        return new ResponseEntity<>(repository.save(createdTeam), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTeamById(@PathVariable("id") Long teamId) {
        if (repository.findOne(teamId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.delete(teamId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateTeam (@PathVariable("id") Long teamId,
                                                @RequestBody final Map<String, String> updatedTeam) {
        Team team = repository.findOne(teamId);
        String field;
        for (String s : TEAM_FIELDS) {
            field = updatedTeam.get(s);
            if (field == null || field.equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        team.setName(updatedTeam.get("name"));
        team.setCar(updatedTeam.get("car"));
        return new ResponseEntity<>(repository.save(team), HttpStatus.OK);
    }



}
