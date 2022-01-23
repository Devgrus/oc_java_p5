package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.Person;
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
public class PersonRepositoryImplTest {

    @Mock
    FakeDatabase db;

    @InjectMocks
    PersonRepositoryImpl personRepository;

    @Test
    public void findAllByAddressTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.findAllByAddress("10 aaa").size()).isEqualTo(4);
    }

    @Test
    public void findAllByAddressTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(personRepository.findAllByAddress(null).size()).isEqualTo(0);
    }

    @Test
    public void findAllByLastNameTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.findAllByLastName("Aen").size()).isEqualTo(4);
    }

    @Test
    public void findAllByLastNameTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(personRepository.findAllByLastName(null).size()).isEqualTo(0);
    }

    @Test
    public void findAllByCityTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.findAllByCity("aaa").size()).isEqualTo(4);
    }

    @Test
    public void findAllByCityTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(personRepository.findAllByCity(null).size()).isEqualTo(0);
    }

    @Test
    public void saveTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        Person newPerson = new Person("Gm", "Aen", "40 ddd", "aaa", 10000, "777-777-7777", "ggg@abc.com");

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.save(newPerson)).isTrue();
    }

    @Test
    public void saveTestParameterValueAlreadyExist() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        Person newPerson = new Person("Am", "Aen", "10 ddd", "aaa", 10000, "777-777-7777", "ggg@abc.com");

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.save(newPerson)).isFalse();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(personRepository.save(null)).isFalse();
    }

    @Test
    public void updateTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        Person newPerson = new Person("Am", "Aen", "40 ddd", "aaa", 10000, "777-777-7777", "ggg@abc.com");

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.update(newPerson)).isTrue();
        assertThat(personList.get(0).getAddress()).isEqualTo("40 ddd");
    }

    @Test
    public void updateTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(personRepository.update(null)).isFalse();
    }

    @Test
    public void deleteTest() {
        //given
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"));
        personList.add(new Person("Bm", "Aen", "10 aaa", "aaa", 10000, "222-222-2222", "bbb@abc.com"));
        personList.add(new Person("Cm", "Aen", "10 aaa", "aaa", 10000, "333-333-3333", "ccc@abc.com"));
        personList.add(new Person("Dm", "Aen", "10 aaa", "aaa", 10000, "444-444-4444", "ddd@abc.com"));
        personList.add(new Person("Em", "Aen", "30 ccc", "aaa", 10000, "555-555-5555", "eee@abc.com"));
        personList.add(new Person("Fm", "Aen", "40 ddd", "aaa", 10000, "666-666-6666", "fff@abc.com"));

        List<String> deleteName = List.of("Am", "Aen");

        //when
        when(db.getPersons()).thenReturn(personList);

        //then
        assertThat(personRepository.delete(deleteName.get(0), deleteName.get(1))).isTrue();
        assertThat(personList.get(0).getFirstName()).isEqualTo("Bm");
    }


    @Test
    public void deleteTestEmptyStrings() {
        //given
        List<String> deleteName = List.of("", "");

        //when

        //then
        assertThat(personRepository.delete(deleteName.get(0), deleteName.get(1))).isFalse();
    }

    @Test
    public void deleteTestParametersAreNull() {
        //given

        //when

        //then
        assertThat(personRepository.delete(null, null)).isFalse();
    }
}
