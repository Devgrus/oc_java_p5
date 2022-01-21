package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.FireStation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireStationRepositoryImpl implements FireStationRepository {

    private FakeDatabase db;

    public FireStationRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public List<FireStation> findFireStationsByStation(Integer station) {
        return db.getFirestations().stream().filter(i-> station == i.getStation()).collect(Collectors.toList());
    }

    @Override
    public FireStation findFireStationByAddress(String address) {
        return null;
    }

    @Override
    public FireStation save(FireStation fireStation) {
        return null;
    }

    @Override
    public Boolean update(FireStation fireStation) {
        return false;
    }

    @Override
    public Boolean delete(FireStation fireStation) {
        return false;
    }
}
