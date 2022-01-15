package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "fire_station")
@Entity
public class FireStation {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int station;

    public FireStation() {}

    public FireStation(Long id, String address, int station) {
        this.id = id;
        this.address = address;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
