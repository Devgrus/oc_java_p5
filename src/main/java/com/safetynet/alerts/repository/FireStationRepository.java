package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepository {

    List<FireStation> findFireStationsByStation(Integer station);

    FireStation findFireStationByAddress(String address);

    FireStation save(FireStation fireStation);

    Boolean update(FireStation fireStation);

    Boolean delete(FireStation fireStation);

}
