package com.mysuperapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by isaevpd-kde on 12/6/16.
 */
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private Long team;

    protected Driver() {}

    public Driver(String firstName, String lastName, String country, Long team) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTeam() {
        return this.team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }
    public String toString() {
       return String.format(
               "Driver %d: first name: %s, last name: %s, country: %s, team: %d.",
               id,
               firstName,
               lastName,
               country,
               team
       );
    }

}
