package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.dto.EmailDto;
import com.safetynet.alerts.service.CommunityEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CommunityEmailService communityEmailService;

    public CommunityEmailController(CommunityEmailService communityEmailService) {
        this.communityEmailService = communityEmailService;
    }

    /**
     *
     * @param city city name
     * @return List of residents' emails
     */
    @GetMapping(value = "/communityEmail")
    public EmailDto getEmailList(@RequestParam String city) {
        logger.info("Request received: GET - /communityEmail, parameter(s) value(s): " + city);

        EmailDto emailDto = communityEmailService.getEmailList(city);

        if(emailDto != null && emailDto.getEmail().size() != 0) {
            logger.info("Result of GET city= " + city);
            return emailDto;
        } else {
            logger.error("city " + city + " does not exist");
            throw new BadRequestException("Wrong parameter value.");
        }
    }
}
