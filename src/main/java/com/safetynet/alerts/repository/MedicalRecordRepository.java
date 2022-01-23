package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository {

    MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName);

    Boolean save(MedicalRecord medicalRecord);

    Boolean update(MedicalRecord medicalRecord);

    Boolean delete(String firstName, String lastName);
}
