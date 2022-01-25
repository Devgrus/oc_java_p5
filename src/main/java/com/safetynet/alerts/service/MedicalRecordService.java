package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public Boolean save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public Boolean update(MedicalRecord medicalRecord) {
        return medicalRecordRepository.update(medicalRecord);
    }

    public Boolean delete(String firstName, String lastName) {
        return medicalRecordRepository.delete(firstName, lastName);
    }
}
