package com.safetynet.alerts.model.dto;

import java.util.List;

public class ChildAlertDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<FamilyMemberDto> householdMember;

    public ChildAlertDto() {
    }

    public ChildAlertDto(String firstName, String lastName, int age, List<FamilyMemberDto> householdMember) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.householdMember = householdMember;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<FamilyMemberDto> getHouseholdMember() {
        return householdMember;
    }

    public void setHouseholdMember(List<FamilyMemberDto> householdMember) {
        this.householdMember = householdMember;
    }
}
