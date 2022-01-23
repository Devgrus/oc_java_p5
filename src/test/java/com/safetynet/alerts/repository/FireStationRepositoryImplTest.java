package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.FireStation;
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
public class FireStationRepositoryImplTest {

    @Mock
    FakeDatabase db;

    @InjectMocks
    FireStationRepositoryImpl fireStationRepository;

    @Test
    public void findFireStationsByStationTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.findFireStationsByStation(1).size()).isEqualTo(2);
    }

    @Test
    public void findFireStationByStationTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(fireStationRepository.findFireStationsByStation(null).size()).isEqualTo(0);
    }

    @Test
    public void findFireStationByAddressTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.findFireStationByAddress("10 aaa").getAddress()).isEqualTo("10 aaa");
    }

    @Test
    public void findFireStationByAddressTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(fireStationRepository.findFireStationByAddress(null)).isNull();
    }

    @Test
    public void saveTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));
        FireStation newFireStation = new FireStation("30 ccc", 1);

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.save(newFireStation)).isTrue();
        assertThat(fireStationList.size()).isEqualTo(3);
    }

    @Test
    public void saveTestParameterValueAlreadyExist() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));
        FireStation newFireStation = new FireStation("10 aaa", 1);

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.save(newFireStation)).isFalse();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.save(null)).isFalse();
        assertThat(fireStationRepository.findFireStationsByStation(1).size()).isEqualTo(2);
    }

    @Test
    public void updateTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));
        FireStation newFireStation = new FireStation("10 aaa", 2);

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.update(newFireStation)).isTrue();
        assertThat(fireStationList.get(0).getStation()).isEqualTo(2);
    }

    @Test
    public void updateTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(fireStationRepository.update(null)).isFalse();
    }

    @Test
    public void deleteByAddressTest() {
        //given
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation("10 aaa", 1));
        fireStationList.add(new FireStation("20 bbb", 1));

        String deleteAddress = "10 aaa";

        //when
        when(db.getFirestations()).thenReturn(fireStationList);

        //then
        assertThat(fireStationRepository.deleteByAddress(deleteAddress)).isTrue();
        assertThat(fireStationList.size()).isEqualTo(1);
    }

    @Test
    public void deleteByAddressParameterIsNull() {
        //given

        //when

        //then
        assertThat(fireStationRepository.deleteByAddress(null)).isFalse();
    }
}
