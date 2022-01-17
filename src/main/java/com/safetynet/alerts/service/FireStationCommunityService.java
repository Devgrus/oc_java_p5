package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.CommunityMemberDto;
import com.safetynet.alerts.model.dto.FireStationCommunityDto;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.util.CalculationAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationCommunityService {

    private FireStationRepository fireStationRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private PersonRepository personRepository;

    @Autowired
    public FireStationCommunityService(FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository, PersonRepository personRepository) {
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.personRepository = personRepository;
    }

    /**
     * get a list of people covered by the fire station
     *
     * @param fireStationNumber number of fire station
     * @return list of persons, number of adults and number of children
     */
    public FireStationCommunityDto getFireStationCommunity(int fireStationNumber) {
        // find by station number fireStationRepository (FireStationAddresses by station number)
        List<FireStation> fireStationList = fireStationRepository.findFireStationsByStation(fireStationNumber); // Get a list of fire station
        List<String> addressList = new ArrayList<>(); // Get a list of address

        if(fireStationList.size() > 0) {
            fireStationList.stream().map(FireStation::getAddress).forEach(addressList::add);
        } else {
            return null;
        }

        List<Person> persons = new ArrayList<>();
        addressList.stream()
                .map(i -> personRepository.findPersonsByAddress(i)) // Get addresses
                .filter(i -> i.size() > 0)
                .forEach(persons::addAll);

        if(persons.size() < 1) {
            return null;
        }

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        List<CommunityMemberDto> communityMemberDtos = new ArrayList<>();

        for(Person person: persons) {
            MedicalRecord medicalRecord = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            if(medicalRecord != null) {
                medicalRecords.add(medicalRecord);
            }
            communityMemberDtos.add(new CommunityMemberDto(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()));
        }

        CalculationAge calculationAge = new CalculationAge();
        int adultCount = medicalRecords.stream().map(i->calculationAge.getAge(i.getBirthdate()) >= 18 ? 1 : 0).reduce(0, Integer::sum);
        int childCount = medicalRecords.size() - adultCount;

        return new FireStationCommunityDto(communityMemberDtos, adultCount, childCount);
    }
}
