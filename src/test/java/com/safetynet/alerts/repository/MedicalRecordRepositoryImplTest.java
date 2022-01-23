package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.MedicalRecord;
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
public class MedicalRecordRepositoryImplTest {

    @Mock
    FakeDatabase db;

    @InjectMocks
    MedicalRecordRepositoryImpl medicalRecordRepository;

    @Test
    public void findMedicalRecordByFirstNameAndLastNameTest() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName("Am", "Aen")).isEqualTo(medicalRecordList.get(0));
    }

    @Test
    public void findMedicalRecordByFirstNameAndLastNameTestParametersAreNull() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(null, null)).isNull();
    }

    @Test
    public void saveTest() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));
        MedicalRecord newMedicalRecord = new MedicalRecord("Gm", "Den", LocalDate.of(2011, 9, 12), List.of(), List.of());

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.save(newMedicalRecord)).isTrue();
    }

    @Test
    public void saveTestNameAlreadyExist() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));
        MedicalRecord newMedicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(2011, 9, 12), List.of(), List.of());

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.save(newMedicalRecord)).isFalse();
    }

    @Test
    public void saveTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(medicalRecordRepository.save(null)).isFalse();
    }

    @Test
    public void updateTest() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));
        MedicalRecord newMedicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(2011, 9, 12), List.of("x"), List.of("y"));

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.update(newMedicalRecord)).isTrue();
        assertThat(medicalRecordList.get(0).getMedications().get(0)).isEqualTo("x");
    }

    @Test
    public void updateTestParameterIsNull() {
        //given

        //when

        //then
        assertThat(medicalRecordRepository.update(null)).isFalse();
    }

    @Test
    public void removeTest() {
        //given
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Bm", "Aen", LocalDate.of(2015, 5, 12), List.of(), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Cm", "Ben", LocalDate.of(1980, 6, 12), List.of("e", "f"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Dm", "Ben", LocalDate.of(2016, 7, 12), List.of(), List.of()));
        medicalRecordList.add(new MedicalRecord("Em", "Cen", LocalDate.of(1992, 8, 12), List.of("i", "j"), List.of("z")));
        medicalRecordList.add(new MedicalRecord("Fm", "Cen", LocalDate.of(2017, 9, 12), List.of(), List.of()));
        List<String> removeName = List.of("Am", "Aen");

        //when
        when(db.getMedicalrecords()).thenReturn(medicalRecordList);

        //then
        assertThat(medicalRecordRepository.delete(removeName.get(0), removeName.get(1))).isTrue();
        assertThat(medicalRecordList.get(0).getFirstName()).isNotEqualTo("Am");
    }

    @Test
    public void deleteTestParametersAreNull() {
        //given

        //when

        //then
        assertThat(medicalRecordRepository.delete(null, null)).isFalse();
    }


}
