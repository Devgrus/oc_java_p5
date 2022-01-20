package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FloodServiceTest {

    @Mock
    FireStationRepository fireStationRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    FloodService floodService;

    @Test
    public void getResidentListByStationsNumbersTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation(1L, "10 aaa", 1));
        fireStationList.add(new FireStation(1L, "20 bbb", 1));
        fireStationList.add(new FireStation(2L, "30 ccc", 2));
        fireStationList.add(new FireStation(2L, "40 ddd", 2));

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person(2L, "Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person(3L, "Cm", "Ben", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person(4L, "Dm", "Ben", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person(5L, "Em", "Cen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person(6L, "Fm", "Cen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord(1L, "Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(2L, "Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord(3L, "Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(4L, "Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord(5L, "Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(6L, "Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when
        when(fireStationRepository.findFireStationsByStation(1)).thenReturn(fireStationList.subList(0,2));
        when(fireStationRepository.findFireStationsByStation(2)).thenReturn(fireStationList.subList(2,4));

        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList.subList(0,2));
        when(personRepository.findAllByAddress("20 bbb")).thenReturn(personList.subList(2,4));
        when(personRepository.findAllByAddress("30 ccc")).thenReturn(personList.subList(4,5));
        when(personRepository.findAllByAddress("40 ddd")).thenReturn(personList.subList(5,6));

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).thenReturn(medicalRecordList.get(0));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Bm", "Aen")).thenReturn(medicalRecordList.get(1));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Cm", "Ben")).thenReturn(medicalRecordList.get(2));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Dm", "Ben")).thenReturn(medicalRecordList.get(3));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Em", "Cen")).thenReturn(medicalRecordList.get(4));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Fm", "Cen")).thenReturn(medicalRecordList.get(5));

        //then
        assertThat(floodService.getResidentListByStationsNumbers(List.of(1, 2)).get(0).getAddress()).isEqualTo("10 aaa");

    }
}
