package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.PersonInfoDto;
import com.safetynet.alerts.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PersonInfoService personInfoService;

    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    /**
     *
     * @param firstName first name
     * @param lastName last name
     * @return list of people who have this last name
     */
    @GetMapping(value = "/personInfo")
    public List<PersonInfoDto> getPersonInfoList(@RequestParam String firstName, @RequestParam String lastName) {
        logger.info("Request received: GET - /personInfo, parameter(s) value(s): firstName = " + firstName + " lastName = " + lastName);

        List<PersonInfoDto> personInfoDtoList = personInfoService.getPersonInfoList(lastName);

        if(personInfoDtoList != null) {
            logger.info("Result of GET lastName= " + lastName);
            return personInfoDtoList;
        } else {
            logger.error("lastName " + lastName + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
