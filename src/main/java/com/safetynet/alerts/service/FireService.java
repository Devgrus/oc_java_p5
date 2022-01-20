package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.FireDto;
import com.safetynet.alerts.model.dto.ResidentDto;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.CalculationAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FireService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public FireService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    public FireDto getResidentList(String address) {
        List<Person> personList = personRepository.findAllByAddress(address);

        if(personList.size() == 0) return null;

        FireStation fireStation = fireStationRepository.findFireStationByAddress(address);

        if(fireStation == null) return null;

        List<MedicalRecord> medicalRecordList = personList.stream().map(i -> medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(i.getFirstName(), i.getLastName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if(medicalRecordList.size() == 0) return null;

        List<ResidentDto> residentDtoList = new ArrayList<>();

        for(MedicalRecord md : medicalRecordList) {
            String phone = "";
            for(Person ps : personList) {
                if(ps.getFirstName().equals(md.getFirstName()) && ps.getLastName().equals(md.getLastName())) {
                    phone = ps.getPhone();
                    break;
                }
            }

            if(!phone.equals(""))
                residentDtoList.add(new ResidentDto(md.getLastName(), phone, CalculationAge.getInstance().getAge(md.getBirthdate()), md.getMedications(), md.getAllergies()));
        }

        return new FireDto(fireStation.getStation(), residentDtoList);
    }
}
