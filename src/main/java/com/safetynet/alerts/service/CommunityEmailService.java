package com.safetynet.alerts.service;

import com.safetynet.alerts.model.dto.EmailDto;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityEmailService {

    private final PersonRepository personRepository;

    public CommunityEmailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     *
     * @param city city name
     * @return List of residents' emails
     */
    public List<EmailDto> getEmailList(String city) {
        List<EmailDto> emailDtoList = new ArrayList<>();

        personRepository.findAllByCity(city).forEach(i -> emailDtoList.add(new EmailDto(i.getEmail())));

        if(emailDtoList.size() == 0) return null;

        return emailDtoList;

    }
}
