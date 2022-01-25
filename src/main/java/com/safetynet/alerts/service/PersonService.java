package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Boolean save(Person person) {
        return personRepository.save(person);
    }

    public Boolean update(Person person) {
        return personRepository.update(person);
    }

    public Boolean delete(String firstName, String lastName) {
        return personRepository.delete(firstName, lastName);
    }
}
