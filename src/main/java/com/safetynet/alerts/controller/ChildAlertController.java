package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.ChildAlertDto;
import com.safetynet.alerts.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChildAlertController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ChildAlertService childAlertService;

    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    /**
     *
     * @param address address
     * @return List of children and their household member
     */
    @GetMapping(value = "/childAlert")
    public List<ChildAlertDto> getChildrenList(@RequestParam String address) {
        logger.info("Request received: GET - /childAlert, parameter(s) value(s): address = " + address);

        List<ChildAlertDto> childAlertDtoList = childAlertService.getChildrenList(address);

        if(childAlertDtoList != null) {
            logger.info("Result of GET address= " + address);
            return childAlertDtoList;
        } else {
            logger.error("address " + address + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
