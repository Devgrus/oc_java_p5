package com.safetynet.alerts.util;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;

@Component
public class CalculationAge {
    /**
     * Calculate age with birthdate
     * @param birthDate birthdate of person
     * @return if birthDate object isn't null, it returns age. Else, it returns -1.
     */
    public int getAge(LocalDate birthDate) {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        if(birthDate != null && now.isAfter(birthDate)) { // birthDate isn't null and now is after birthdate
            return Period.between(birthDate, now).getYears();
        }
        return -1;
    }
}
