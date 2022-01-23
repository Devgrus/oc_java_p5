package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    List<Person> findAllByAddress(String address);

    List<Person> findAllByLastName(String lastName);

    List<Person> findAllByCity(String city);

    Boolean save(Person person);

    Boolean update(Person person);

    Boolean delete(String firstName, String lastName);
}
