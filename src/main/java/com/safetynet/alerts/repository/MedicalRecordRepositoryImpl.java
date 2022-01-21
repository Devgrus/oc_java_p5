package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private FakeDatabase db;

    public MedicalRecordRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        return db.getMedicalrecords().stream().filter(i->firstName.equals(i.getFirstName()) && lastName.equals(i.getLastName())).findFirst().get();
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public Boolean update(MedicalRecord medicalRecord) {
        return false;
    }

    @Override
    public Boolean delete(MedicalRecord medicalRecord) {
        return false;
    }
}
