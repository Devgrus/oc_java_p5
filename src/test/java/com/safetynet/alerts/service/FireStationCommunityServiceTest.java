package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FireStationCommunityServiceTest {

    @Mock
    FireStationRepository fireStationRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    FireStationCommunityService fireStationCommunityService;

    @Test
    public void getFireStationCommunityTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Ben", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Ben", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Cen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Cen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(fireStationList);

        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList.subList(0,2));
        when(personRepository.findAllByAddress("20 bbb")).thenReturn(personList.subList(2,4));

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).thenReturn(medicalRecordList.get(0));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Bm", "Aen")).thenReturn(medicalRecordList.get(1));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Cm", "Ben")).thenReturn(medicalRecordList.get(2));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Dm", "Ben")).thenReturn(medicalRecordList.get(3));

        //then
        assertThat(fireStationCommunityService.getFireStationCommunity(1).getAdultCount()).isEqualTo(2);
        assertThat(fireStationCommunityService.getFireStationCommunity(1).getChildCount()).isEqualTo(2);
    }

    @Test
    public void getFireStationCommunityTestFireStationListIsNull() {
        //given

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(new ArrayList<>());

        //then
        assertThat(fireStationCommunityService.getFireStationCommunity(1)).isEqualTo(null);
    }

    @Test
    public void getFireStationCommunityTestPersonsListIsNull() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(fireStationList);
        when(personRepository.findAllByAddress(anyString())).thenReturn(new ArrayList<>());

        //then
        assertThat(fireStationCommunityService.getFireStationCommunity(1)).isEqualTo(null);
    }
}
