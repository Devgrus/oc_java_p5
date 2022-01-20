package com.safetynet.alerts.model.dto;

import java.util.List;

public class FloodDto {
    private String address;
    private List<ResidentDto> resident;

    public FloodDto() {
    }

    public FloodDto(String address, List<ResidentDto> resident) {
        this.address = address;
        this.resident = resident;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ResidentDto> getResident() {
        return resident;
    }

    public void setResident(List<ResidentDto> resident) {
        this.resident = resident;
    }

}
