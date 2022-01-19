package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.PhoneAlertDto;
import com.safetynet.alerts.service.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PhoneAlertService phoneAlertService;

    public PhoneAlertController(PhoneAlertService phoneAlertService) {
        this.phoneAlertService = phoneAlertService;
    }

    /**
     *
     * @param firestation fire station number
     * @return List of phone number
     */
    @GetMapping(value="/phoneAlert")
    public List<PhoneAlertDto> getPhoneList(@RequestParam Integer firestation) {
        logger.info("Request received: GET - /phoneAlert parameter(s) value(s): firestation = " + firestation);

        List<PhoneAlertDto> phoneAlertDtoList = phoneAlertService.getPhoneList(firestation);

        if(phoneAlertDtoList != null) {
            logger.info("Result of GET firestation=" + firestation);
            return phoneAlertDtoList;
        } else {
            logger.error("firestation " + firestation.toString() + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
