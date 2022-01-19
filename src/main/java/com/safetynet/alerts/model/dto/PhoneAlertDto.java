package com.safetynet.alerts.model.dto;

public class PhoneAlertDto {
    private String phone;

    public PhoneAlertDto() {
    }

    public PhoneAlertDto(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
