package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ChildAlertServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    ChildAlertService childAlertService;


    @Test
    public void getChildrenListTest() {
        //given
        LocalDate now = LocalDate.of(2022, 7, 1);
        Clock clock = Clock.fixed(now.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        String testAddress = "10 aaa";

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person(2L, "Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person(3L, "Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person(4L, "Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person(5L, "Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person(6L, "Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));



        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord(1L, "Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(2L, "Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord(3L, "Cm", "Aen", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(4L, "Dm", "Aen", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord(5L, "Em", "Aen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord(6L, "Fm", "Aen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when

        mockStatic(Clock.class)
                .when(Clock::systemDefaultZone)
                .thenReturn(clock);

        when(personRepository.findPersonsByAddress(testAddress)).thenReturn(personList.subList(0,4));

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).thenReturn(medicalRecordList.get(0));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Bm", "Aen")).thenReturn(medicalRecordList.get(1));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Cm", "Aen")).thenReturn(medicalRecordList.get(2));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Dm", "Aen")).thenReturn(medicalRecordList.get(3));


        //then
        assertThat(childAlertService.getChildrenList(testAddress).get(0).getFirstName()).isEqualTo("Bm");
        assertThat(childAlertService.getChildrenList(testAddress).get(1).getFirstName()).isEqualTo("Dm");
    }

    @Test
    public void getChildrenListTestPersonListIsNull() {
        //given

        //when
        when(personRepository.findPersonsByAddress(anyString())).thenReturn(new ArrayList<>());

        //then
        assertThat(childAlertService.getChildrenList(anyString())).isNull();
    }

    @Test
    public void getChildrenListTestMedicalRecordListIsNull() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person(2L, "Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));

        //when
        when(personRepository.findPersonsByAddress(anyString())).thenReturn(personList);
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);

        //then
        assertThat(childAlertService.getChildrenList(anyString())).isNull();
    }
}
