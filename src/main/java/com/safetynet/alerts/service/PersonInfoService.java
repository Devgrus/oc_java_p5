package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.PersonInfoDto;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.CalculationAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonInfoService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public PersonInfoService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     *
     * @param lastName last name
     * @return list of people who has this last name
     */
    public List<PersonInfoDto> getPersonInfoList(String lastName) {
        List<Person> personList = personRepository.findAllByLastName(lastName);
        if(personList.size() == 0) return null;

        List<PersonInfoDto> personInfoDtoList = new ArrayList<>();

        for(Person ps : personList) {
            MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(ps.getFirstName(), ps.getLastName());
            if(medicalRecord == null) continue;

            personInfoDtoList.add(new PersonInfoDto(ps.getLastName(), ps.getAddress(), CalculationAge.getInstance().getAge(medicalRecord.getBirthdate()), ps.getEmail(), medicalRecord.getMedications(), medicalRecord.getAllergies()));
        }

        if(personInfoDtoList.size() == 0) return null;

        return personInfoDtoList;
    }
}
