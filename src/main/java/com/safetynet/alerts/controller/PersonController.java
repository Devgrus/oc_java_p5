package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody Person person) {
        logger.info("Request received: POST - /person");
        if(personService.save(person)) {
            logger.info("Add a person in DB");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.error("Can not add this person. This person may exist in DB");
            throw new BadRequestException("Can not add this person. This person may exist in DB");
        }
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Person person) {
        logger.info("Request received: PUT - /person");
        if(personService.update(person)) {
            logger.info("Update person");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not update this person");
            throw new BadRequestException("Can not update this person");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam String firstName,@RequestParam String lastName) {
        logger.info("Request received: DELETE - /person");
        if(personService.delete(firstName, lastName)) {
            logger.info("Delete person");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not delete this person");
            throw new BadRequestException("Can not delete this person");
        }
    }
}