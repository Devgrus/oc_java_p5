package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {

    @Mock
    FireStationRepository fireStationRepository;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PhoneAlertService phoneAlertService;

    @Test
    public void getPhoneListTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(fireStationList);
        when(personRepository.findAllByAddress("10 aaa")).thenReturn(personList.subList(0, 2));
        when(personRepository.findAllByAddress("20 bbb")).thenReturn(personList.subList(2, 4));

        //then
        assertThat(phoneAlertService.getPhoneList(1).size()).isEqualTo(4);

    }

    @Test
    public void getPhoneListTestFireStationListIsEmpty() {
        //given

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(new ArrayList<>());

        //then
        assertThat(phoneAlertService.getPhoneList(1)).isNull();

    }

    @Test
    public void getPhoneListTestPersonListIsEmpty() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        //when
        when(fireStationRepository.findFireStationsByStation(anyInt())).thenReturn(fireStationList);
        when(personRepository.findAllByAddress(anyString())).thenReturn(new ArrayList<>());

        //then
        assertThat(phoneAlertService.getPhoneList(1)).isNull();

    }
}
