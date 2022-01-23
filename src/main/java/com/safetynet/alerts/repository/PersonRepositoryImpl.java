package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final FakeDatabase db;

    public PersonRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public List<Person> findAllByAddress(String address) {
        return db.getPersons().stream().filter(i->i.getAddress().equals(address)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        return db.getPersons().stream().filter(i->i.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByCity(String city) {
        return db.getPersons().stream().filter(i->i.getCity().equals(city)).collect(Collectors.toList());
    }

    @Override
    public Boolean save(Person person) {
            if(person != null &&
                    db.getPersons().stream()
                    .filter(i->i.getFirstName().equals(person.getFirstName()) && i.getLastName().equals(person.getLastName()))
                    .findFirst()
                    .isEmpty()) {
                db.getPersons().add(person);
                return true;
            }
        return false;
    }

    @Override
    public Boolean update(Person person) {
        if(person != null) {
            Optional<Person> optionalPerson = db.getPersons().stream()
                    .filter(i-> i.getFirstName().equals(person.getFirstName()) && i.getLastName().equals(person.getLastName()))
                    .findFirst();

            if(optionalPerson.isPresent()) {
                Person ps = optionalPerson.get();
                ps.setAddress(person.getAddress());
                ps.setCity(person.getCity());
                ps.setEmail(person.getEmail());
                ps.setZip(person.getZip());
                ps.setPhone(person.getPhone());
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean delete(String firstName, String lastName) {
        if(firstName != null && lastName != null) {
            Optional<Person> personResult = db.getPersons().stream()
                    .filter(i-> i.getFirstName().equals(firstName) && i.getLastName().equals(lastName))
                    .findFirst();

            if(personResult.isPresent()) {
                db.getPersons().remove(personResult.get());
                return true;
            }
        }
        return false;
    }
}
