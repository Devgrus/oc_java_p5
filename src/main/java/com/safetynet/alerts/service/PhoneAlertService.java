package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.dto.PhoneAlertDto;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertService {

    private final FireStationRepository fireStationRepository;

    private final PersonRepository personRepository;

    @Autowired
    public PhoneAlertService(FireStationRepository fireStationRepository, PersonRepository personRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
    }

    /**
     *
     * @param stationNumber station number
     * @return list of person who live at this station's address
     */
    public PhoneAlertDto getPhoneList(int stationNumber) {
        List<FireStation> fireStationList = fireStationRepository.findFireStationsByStation(stationNumber);

        if(fireStationList.size() == 0) return null;

        List<String> phoneList = new ArrayList<>();

        fireStationList.stream()
                .map(i -> personRepository.findAllByAddress(i.getAddress()))
                .flatMap(List::stream)
                .forEach(i -> phoneList.add(i.getPhone()));

        if(phoneList.size() == 0) return null;

        return new PhoneAlertDto(phoneList);
    }
}
