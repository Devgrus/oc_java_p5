package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.FloodDto;
import com.safetynet.alerts.model.dto.ResidentDto;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.CalculationAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloodService {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public FloodService(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     *
     * @param stations List of stations numbers
     * @return List of address and resident information at this address
     */
    public List<FloodDto> getResidentListByStationsNumbers(List<Integer> stations) {
        List<FireStation> fireStationList = stations.stream()
                .map(fireStationRepository::findFireStationsByStation)
                .flatMap(Collection::stream).collect(Collectors.toList());

        if(fireStationList.size() == 0) return null;


        List<FloodDto> floodDtoList = new ArrayList<>();

        for(FireStation fs : fireStationList) {
            List<Person> personList = personRepository.findAllByAddress(fs.getAddress());
            if(personList.size() == 0) continue;

            List<ResidentDto> residentDtoList = new ArrayList<>();

            for(Person ps : personList) {
                MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(ps.getFirstName(), ps.getLastName());
                if(medicalRecord == null) continue;

                residentDtoList.add(new ResidentDto(ps.getLastName(), ps.getPhone(), CalculationAge.getInstance().getAge(medicalRecord.getBirthdate()), medicalRecord.getMedications(), medicalRecord.getAllergies()));
            }

            floodDtoList.add(new FloodDto(fs.getAddress(), residentDtoList));
        }

        return floodDtoList;
    }

}
