package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

    @Mock
    FireStationRepository fireStationRepository;

    @InjectMocks
    FireStationService fireStationService;

    @Test
    public void saveTest() {
        //given
        FireStation fireStation = new FireStation("20 bbb", 1);

        //when
        when(fireStationRepository.save(fireStation)).thenReturn(true);

        //then
        assertThat(fireStationService.save(fireStation)).isTrue();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given
        FireStation fireStation = new FireStation("20 bbb", 1);

        //when
        when(fireStationRepository.save(null)).thenReturn(false);

        //then
        assertThat(fireStationService.save(null)).isFalse();
    }

    @Test
    public void updateTest() {
        //given
        FireStation fireStation = new FireStation("20 bbb", 1);

        //when
        when(fireStationRepository.update(fireStation)).thenReturn(true);

        //then
        assertThat(fireStationService.update(fireStation)).isTrue();
    }

    @Test
    public void updateTestParameterIsNull() {
        //given
        FireStation fireStation = new FireStation("20 bbb", 1);

        //when
        when(fireStationRepository.update(null)).thenReturn(false);

        //then
        assertThat(fireStationService.update(null)).isFalse();
    }

    @Test
    public void deleteTest() {
        //given
        String address = "20 bbb";

        //when
        when(fireStationRepository.deleteByAddress(address)).thenReturn(true);

        //then
        assertThat(fireStationService.deleteByAddress(address)).isTrue();
    }

    @Test
    public void deleteTestParameterIsNull() {
        //given
        FireStation fireStation = new FireStation("20 bbb", 1);

        //when
        when(fireStationRepository.deleteByAddress(null)).thenReturn(false);

        //then
        assertThat(fireStationService.deleteByAddress(null)).isFalse();
    }
}
