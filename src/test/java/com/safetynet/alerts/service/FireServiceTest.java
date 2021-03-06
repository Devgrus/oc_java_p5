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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FireServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    FireStationRepository fireStationRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    FireService fireService;

    @Test
    public void getResidentListTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        FireStation fireStation = new FireStation("10 aaa", 1);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));

        //when
        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList);

        when(fireStationRepository.findFireStationByAddress("10 aaa")).thenReturn(fireStation);

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).thenReturn(medicalRecordList.get(0));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Bm", "Aen")).thenReturn(medicalRecordList.get(1));

        //then
        assertThat(fireService.getResidentList("10 aaa").getResident().size()).isEqualTo(2);
        assertThat(fireService.getResidentList("10 aaa").getResident().get(0).getPhone()).isEqualTo("111-111-1111");

    }

    @Test
    public void getResidentListTestPersonListIsEmpty() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        FireStation fireStation = new FireStation("10 aaa", 1);

        //when
        when(personRepository.findAllByAddress("10 aaa")).thenReturn(new ArrayList<>());

        //then
        assertThat(fireService.getResidentList("10 aaa")).isNull();

    }

    @Test
    public void getResidentListTestFireStationIsNull() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        FireStation fireStation = new FireStation("10 aaa", 1);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));

        //when
        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList);
        when(fireStationRepository.findFireStationByAddress("10 aaa")).thenReturn(null);

        //then
        assertThat(fireService.getResidentList("10 aaa")).isNull();

    }

    @Test
    public void getResidentListTestMedicalRecordListIsEmpty() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        FireStation fireStation = new FireStation("10 aaa", 1);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));

        //when
        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList);

        when(fireStationRepository.findFireStationByAddress("10 aaa")).thenReturn(fireStation);
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);

        //then
        assertThat(fireService.getResidentList("10 aaa")).isNull();

    }

}
