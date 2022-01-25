package com.safetynet.alerts.controller;

import static com.safetynet.alerts.GlobalCatcher.*;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("")
    public ResponseEntity<String> save(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Request received: POST - /medicalRecord");

        if(medicalRecordService.save(medicalRecord)) {
            logger.info("Add this medical record in DB");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.error("Can not add this medical record");
            throw new BadRequestException("Can not add this medical record");
        }
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Request received: PUT - /medicalRecord");

        if(medicalRecordService.update(medicalRecord)) {
            logger.info("Update this medical record");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not update this medical record");
            throw new BadRequestException("Can not update this medical record");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam String firstName,@RequestParam String lastName) {
        logger.info("Request received: DELETE - /medicalRecord");

        if(medicalRecordService.delete(firstName, lastName)) {
            logger.info("Delete this medical record");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("Can not delete this medical record");
            throw new BadRequestException("Can not delete this medical record");
        }
    }
}
