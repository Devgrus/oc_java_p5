package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private FakeDatabase db;

    public MedicalRecordRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        Optional<MedicalRecord> optionalMedicalRecord = db.getMedicalrecords().stream()
                .filter(i->i.getFirstName().equals(firstName) && i.getLastName().equals(lastName))
                .findFirst();

        return optionalMedicalRecord.orElse(null);
    }

    @Override
    public Boolean save(MedicalRecord medicalRecord) {
        if(medicalRecord != null && db.getMedicalrecords().stream()
                .filter(i->i.getFirstName().equals(medicalRecord.getFirstName()) && i.getLastName().equals(medicalRecord.getLastName()))
                .findFirst().isEmpty()) {
            db.getMedicalrecords().add(medicalRecord);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(MedicalRecord medicalRecord) {
        if(medicalRecord != null) {
            Optional<MedicalRecord> optionalMedicalRecord = db.getMedicalrecords().stream()
                    .filter(i->i.getFirstName().equals(medicalRecord.getFirstName()) && i.getLastName().equals(medicalRecord.getLastName()))
                    .findFirst();

            if(optionalMedicalRecord.isPresent()) {
                MedicalRecord mr = optionalMedicalRecord.get();
                mr.setBirthDate(medicalRecord.getBirthdate());
                mr.setAllergies(medicalRecord.getAllergies());
                mr.setMedications(medicalRecord.getMedications());
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(String firstName, String lastName) {
        if(firstName != null & lastName != null) {
            Optional<MedicalRecord> optionalMedicalRecord = db.getMedicalrecords().stream()
                    .filter(i->i.getFirstName().equals(firstName) && i.getLastName().equals(lastName))
                    .findFirst();

            if(optionalMedicalRecord.isPresent()) {
                db.getMedicalrecords().remove(optionalMedicalRecord.get());

                return true;
            }
        }
        return false;
    }
}
