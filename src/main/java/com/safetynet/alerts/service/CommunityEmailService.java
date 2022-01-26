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
    public EmailDto getEmailList(String city) {
        List<String> emailList = new ArrayList<>();

        personRepository.findAllByCity(city).forEach(i -> emailList.add(i.getEmail()));

        if(emailList.size() == 0) return null;

        return new EmailDto(emailList);

    }
}
