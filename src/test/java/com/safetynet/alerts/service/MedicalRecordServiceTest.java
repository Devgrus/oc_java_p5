package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    MedicalRecordService medicalRecordService;

    @Test
    public void saveTest() {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordRepository.save(medicalRecord)).thenReturn(true);

        //then
        assertThat(medicalRecordService.save(medicalRecord)).isTrue();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given

        //when
        when(medicalRecordRepository.save(null)).thenReturn(false);

        //then
        assertThat(medicalRecordService.save(null)).isFalse();
    }

    @Test
    public void updateTest() {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordRepository.update(medicalRecord)).thenReturn(true);

        //then
        assertThat(medicalRecordService.update(medicalRecord)).isTrue();
    }

    @Test
    public void updateTestParameterIsNull() {
        //given

        //when
        when(medicalRecordRepository.update(null)).thenReturn(false);

        //then
        assertThat(medicalRecordService.update(null)).isFalse();
    }

    @Test
    public void deleteTest() {
        //given
        String firstName = "Ae";
        String lastName = "Aen";

        //when
        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(true);

        //then
        assertThat(medicalRecordService.delete(firstName, lastName)).isTrue();
    }

    @Test
    public void deleteTestParametersAreNull() {
        //given

        //when
        when(medicalRecordRepository.delete(null, null)).thenReturn(false);

        //then
        assertThat(medicalRecordService.delete(null, null)).isFalse();
    }
}
