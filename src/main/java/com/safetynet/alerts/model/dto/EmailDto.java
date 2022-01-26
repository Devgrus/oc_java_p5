package com.safetynet.alerts.model.dto;

import java.util.List;

public class EmailDto {
    private List<String> email;

    public EmailDto() {
    }

    public EmailDto(List<String> email) {
        this.email = email;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
