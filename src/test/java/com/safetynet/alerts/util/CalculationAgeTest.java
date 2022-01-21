package com.safetynet.alerts.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.*;

@ExtendWith(MockitoExtension.class)
public class CalculationAgeTest {

    private static MockedStatic<Clock> mockedStatic;

    @BeforeAll
    static void initBefore() {
        LocalDate now = LocalDate.of(2022, 7, 1);
        Clock clock = Clock.fixed(now.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        mockedStatic = mockStatic(Clock.class);
        mockedStatic.when(Clock::systemDefaultZone)
                .thenReturn(clock);
    }

    @AfterAll
    static void close() {
        mockedStatic.close();
    }

    @Test
    public void getAgeTestNullParam() throws Exception {
        //given

        //when

        //then
        assertThat(CalculationAge.getInstance().getAge(null)).isEqualTo(-1);
    }


    @Test
    public void getAgeTestBirthDateLessThanOneYear() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2022, 1, 1);

        //when

        //then
        assertThat(CalculationAge.getInstance().getAge(birthDate)).isEqualTo(0);
    }

    @Test
    public void getAgeTestBirthDateLateThanNow() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2022, 8, 15);

        //when

        //then
        assertThat(CalculationAge.getInstance().getAge(birthDate)).isEqualTo(-1);
    }

    @Test
    public void getAgeTestBirthDateEqualTenYear() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2012, 7, 1);

        //when

        //then
        assertThat(CalculationAge.getInstance().getAge(birthDate)).isEqualTo(10);
    }
}
