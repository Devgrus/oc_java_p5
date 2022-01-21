package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDatabase {
    @JsonProperty("persons")
    List<Person> persons = new ArrayList<>();

    @JsonProperty("firestations")
    List<FireStation> firestations = new ArrayList<>();

    @JsonProperty("medicalrecords")
    List<MedicalRecord> medicalrecords = new ArrayList<>();

    @PostConstruct
    public void init() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource classPathResource = new ClassPathResource("data.json");
        FakeDatabase fakeDatabase = objectMapper.readValue(classPathResource.getFile(), FakeDatabase.class);
        this.persons = fakeDatabase.getPersons();
        this.firestations = fakeDatabase.getFirestations();
        this.medicalrecords = fakeDatabase.getMedicalrecords();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
