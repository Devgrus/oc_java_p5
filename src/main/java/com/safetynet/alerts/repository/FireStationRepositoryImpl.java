package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FakeDatabase;
import com.safetynet.alerts.model.FireStation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FireStationRepositoryImpl implements FireStationRepository {

    private FakeDatabase db;

    public FireStationRepositoryImpl(FakeDatabase db) {
        this.db = db;
    }

    @Override
    public List<FireStation> findFireStationsByStation(Integer station) {
        return db.getFirestations().stream()
                .filter(i-> station == i.getStation())
                .collect(Collectors.toList());
    }

    @Override
    public FireStation findFireStationByAddress(String address) {
        Optional<FireStation> resultFireStation = db.getFirestations().stream().filter(i->i.getAddress().equals(address)).findFirst();
        return resultFireStation.orElse(null);
    }

    @Override
    public Boolean save(FireStation fireStation) {
        if(fireStation != null &&
                db.getFirestations().stream()
                        .filter(i->i.getAddress().equals(fireStation.getAddress()))
                        .findFirst().isEmpty()) {
            db.getFirestations().add(fireStation);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(FireStation fireStation) {
        if(fireStation != null) {
            Optional<FireStation> optionalFireStation = db.getFirestations().stream()
                    .filter(i->i.getAddress().equals(fireStation.getAddress()))
                    .findFirst();

            if(optionalFireStation.isPresent()) {
                optionalFireStation.get().setStation(fireStation.getStation());
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean deleteByAddress(String address) {
        Optional<FireStation> optionalFireStation = db.getFirestations().stream().filter(i->i.getAddress().equals(address)).findFirst();

        if(optionalFireStation.isPresent()) {
            db.getFirestations().remove(optionalFireStation.get());

            return true;
        }
        return false;
    }
}
