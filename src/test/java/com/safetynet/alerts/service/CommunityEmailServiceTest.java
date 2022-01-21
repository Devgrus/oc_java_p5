package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
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
public class CommunityEmailServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    CommunityEmailService communityEmailService;

    @Test
    public void getEmailListTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Ben", "20 bbb", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Ben", "20 bbb", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Cen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Cen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        //when
        when(personRepository.findAllByCity(anyString())).thenReturn(personList);

        //then
        assertThat(communityEmailService.getEmailList("aaa").get(0).getEmail()).isEqualTo("aaa@abc.com");
    }

    @Test
    public void getEmailListTestWithNoPersonList() {
        //given

        //when
        when(personRepository.findAllByCity(anyString())).thenReturn(new ArrayList<>());

        //then
        assertThat(communityEmailService.getEmailList("aaa")).isNull();
    }
}
