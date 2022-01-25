package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.dto.FireStationCommunityDto;
import com.safetynet.alerts.service.FireStationCommunityService;
import com.safetynet.alerts.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FireStationCommunityService fireStationCommunityService;
    private final FireStationService fireStationService;

    public FireStationController(FireStationCommunityService fireStationCommunityService, FireStationService fireStationService) {
        this.fireStationCommunityService = fireStationCommunityService;
        this.fireStationService = fireStationService;
    }

    /**
     *
     * @param stationNumber station number
     * @return List of persons' information, number of adults and number of children
     */
    @GetMapping(value = "")
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

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody FireStation fireStation) {
        logger.info("Request received: POST - /firestation");
        if(fireStationService.save(fireStation)) {
            logger.info("Add a firestation in DB");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.error("This firestation may exist in DB");
            throw new BadRequestException("Wrong value : this firestation may exist in DB");
        }
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody FireStation fireStation) {
        logger.info("Request received: PUT - /firestation");
        if(fireStationService.update(fireStation)) {
            logger.info("Update this firestation");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not update this firestation");
            throw new BadRequestException("Can not update this firestation");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteByAddress(@RequestParam String address) {
        logger.info("Request received: DELETE - /firestation");
        if(fireStationService.deleteByAddress(address)) {
            logger.info("Delete firestation");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not delete this firestation");
            throw new BadRequestException("Can not delete this firestation");
        }
    }
}
