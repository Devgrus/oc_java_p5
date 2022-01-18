package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.FireStationCommunityDto;
import com.safetynet.alerts.service.FireStationCommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireStationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FireStationCommunityService fireStationCommunityService;

    public FireStationController(FireStationCommunityService fireStationCommunityService) {
        this.fireStationCommunityService = fireStationCommunityService;
    }

    /**
     *
     * @param stationNumber station number
     * @return List of persons' information, number of adults and number of children
     */
    @GetMapping(value = "/firestation")
    public FireStationCommunityDto getFireStationCommunity(@RequestParam Integer stationNumber) {
        logger.info("Request received : GET - /firestation, parameter(s) value(s): stationNumber = " + stationNumber.toString());

        FireStationCommunityDto fireStationCommunityDto = fireStationCommunityService.getFireStationCommunity(stationNumber);

        if(fireStationCommunityDto != null) {
            logger.info("Result of GET stationNumber= " + stationNumber.toString());
            return fireStationCommunityDto;
        } else {
            logger.error("stationNumber " + stationNumber.toString() + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
