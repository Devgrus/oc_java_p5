package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.FireDto;
import com.safetynet.alerts.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    /**
     *
     * @param address address
     * @return fire station number and list of resident
     */
    @GetMapping(value = "/fire")
    public FireDto getResidentList(@RequestParam String address) {
        logger.info("Request received: GET - /fire, parameter(s) value(s): address = " + address);

        FireDto fireDto = fireService.getResidentList(address);

        if(fireDto != null) {
            logger.info("Result of GET address" + address);
            return fireDto;
        } else {
            logger.error("address " + address + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
