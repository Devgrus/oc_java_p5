package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final FakeDatabase db;

    public PersonRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public List<Person> findAllByAddress(String address) {
        return db.getPersons().stream().filter(i->address.equals(i.getAddress())).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        return new ArrayList<>();
    }

    @Override
    public List<Person> findAllByCity(String city) {
        return db.getPersons().stream().filter(i->city.equals(i.getCity())).collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Boolean update(Person person) {
        return false;
    }

    @Override
    public Boolean delete(Person person) {
        return false;
    }
}
