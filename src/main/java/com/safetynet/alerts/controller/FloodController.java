package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.FloodDto;
import com.safetynet.alerts.service.FloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/flood")
public class FloodController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FloodService floodService;

    public FloodController(FloodService floodService) {
        this.floodService = floodService;
    }

    @GetMapping(value = "/stations")
    public List<FloodDto> getResidentListByStationsNumbers(@RequestParam List<Integer> stations) {
        logger.info("Request received: GET - /flood/stations, parameter(s) value(s): address = " + stations.toString());

        List<FloodDto> floodDtoList = floodService.getResidentListByStationsNumbers(stations);

        if(floodDtoList != null && floodDtoList.size() != 0) {
            logger.info("Result of GET stations" + stations.toString());
            return floodDtoList;
        } else {
            logger.error("stations " + stations.toString() + " does not exist");
            throw new BadRequestException("Wrong parameter values.");
        }
    }
}
