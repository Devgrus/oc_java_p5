package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    public void saveTest() {
        //given
        Person person = new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com");

        //when
        when(personRepository.save(person)).thenReturn(true);

        //then
        assertThat(personService.save(person)).isTrue();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given

        //when
        when(personRepository.save(null)).thenReturn(false);

        //then
        assertThat(personService.save(null)).isFalse();
    }

    @Test
    public void updateTest() {
        //given
        Person person = new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com");

        //when
        when(personRepository.update(person)).thenReturn(true);

        //then
        assertThat(personService.update(person)).isTrue();
    }

    @Test
    public void updateTestParameterIsNull() {
        //given

        //when
        when(personRepository.update(null)).thenReturn(false);

        //then
        assertThat(personService.update(null)).isFalse();
    }

    @Test
    public void deleteTest() {
        //given
        String firstName = "Ae";
        String lastName = "Aen";

        //when
        when(personRepository.delete(firstName, lastName)).thenReturn(true);

        //then
        assertThat(personService.delete(firstName, lastName)).isTrue();
    }

    @Test
    public void deleteTestParametersAreNull() {
        //given

        //when
        when(personRepository.delete(null, null)).thenReturn(false);

        //then
        assertThat(personService.delete(null, null)).isFalse();
    }
}
