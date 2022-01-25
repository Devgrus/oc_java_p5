package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public Boolean save(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    public Boolean update(FireStation fireStation) {
        return fireStationRepository.update(fireStation);
    }

    public Boolean deleteByAddress(String address) {
        return fireStationRepository.deleteByAddress(address);
    }

}
