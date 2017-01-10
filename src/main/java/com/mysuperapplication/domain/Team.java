package com.mysuperapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by isaevpd-kde on 12/6/16.
 */
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String car;

    protected Team() {}

    public Team(String name, String car) {
        super();
        this.name = name;
        this.car = car;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String toString() {
        return String.format(
                "Team %d: name: %s, car: %s.",
                id,
                name,
                car
        );
    }
}
