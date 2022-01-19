package com.safetynet.alerts.model.dto;

import java.util.List;

public class FireDto {
    private int station;
    private List<ResidentDto> resident;

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public List<ResidentDto> getResident() {
        return resident;
    }

    public void setResident(List<ResidentDto> resident) {
        this.resident = resident;
    }

    public FireDto(int station, List<ResidentDto> resident) {
        this.station = station;
        this.resident = resident;
    }

    public FireDto() {
    }
}
