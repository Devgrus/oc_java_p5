package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
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
public class PersonInfoServiceTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    PersonInfoService personInfoService;

    @Test
    public void getPersonInfoListTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Aen", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Aen", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Aen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Aen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when
        when(personRepository.findAllByLastName("Aen")).thenReturn(personList);

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).thenReturn(medicalRecordList.get(0));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Bm", "Aen")).thenReturn(medicalRecordList.get(1));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Cm", "Aen")).thenReturn(medicalRecordList.get(2));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Dm", "Aen")).thenReturn(medicalRecordList.get(3));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Em", "Aen")).thenReturn(medicalRecordList.get(4));
        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Fm", "Aen")).thenReturn(medicalRecordList.get(5));

        //then
        assertThat(personInfoService.getPersonInfoList("Aen").get(0).getEmail()).isEqualTo("aaa@abc.com");
    }

    @Test
    public void getPersonInfoListTestWithPersonListIsEmpty() {
        //given

        //when
        when(personRepository.findAllByLastName(anyString())).thenReturn(new ArrayList<>());

        //then
        assertThat(personInfoService.getPersonInfoList("Aen")).isNull();
    }

    @Test
    public void getPersonInfoListTestMedicalRecordListIsEmpty() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        //when
        when(personRepository.findAllByLastName("Aen")).thenReturn(personList);

        when(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(anyString(), anyString())).thenReturn(null);

        //then
        assertThat(personInfoService.getPersonInfoList("Aen")).isNull();
    }

}
