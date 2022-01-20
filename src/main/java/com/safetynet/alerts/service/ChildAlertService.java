package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.ChildAlertDto;
import com.safetynet.alerts.model.dto.FamilyMemberDto;
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
public class ChildAlertService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public ChildAlertService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     *
     * @param address address
     * @return list of children
     */
    public List<ChildAlertDto> getChildrenList(String address) {
        List<Person> personList = personRepository.findAllByAddress(address); // find all persons who live at this address

        if(personList.size() == 0) {
            return null;
        }

        List<MedicalRecord> medicalRecordList = personList.stream()
                .map(i -> medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(i.getFirstName(), i.getLastName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()); // find their medical record by first name and last name

        if(medicalRecordList.size() == 0) {
            return null;
        }

        List<ChildAlertDto> childAlertDtoList = new ArrayList<>();

        for(MedicalRecord mr :medicalRecordList) {
            int age = CalculationAge.getInstance().getAge(mr.getBirthdate());
            if(age <= 18) {
                List<FamilyMemberDto> familyMemberDtoList = new ArrayList<>();
                personList.stream()
                        .filter(i -> !mr.getFirstName().equals(i.getFirstName()) && mr.getLastName().equals(i.getLastName()))
                        .forEach(i -> familyMemberDtoList.add(new FamilyMemberDto(i.getFirstName(), i.getLastName(), i.getPhone(), i.getEmail())));
                childAlertDtoList.add(new ChildAlertDto(mr.getFirstName(), mr.getLastName(), age, familyMemberDtoList));
            }
        }
        return childAlertDtoList;
    }
}
