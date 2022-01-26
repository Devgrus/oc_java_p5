package com.safetynet.alerts.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PhoneAlertDto {
    private List<String> phone;

    public PhoneAlertDto() {
    }

    public PhoneAlertDto(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
