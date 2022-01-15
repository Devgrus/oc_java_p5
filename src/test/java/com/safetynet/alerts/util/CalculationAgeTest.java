package com.safetynet.alerts.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.*;

@SpringBootTest
public class CalculationAgeTest {

    @InjectMocks
    CalculationAge calculationAge;

    @BeforeEach
    public void initEach() {
        LocalDate now = LocalDate.of(2022, 7, 1);
        Clock clock = Clock.fixed(now.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        mockStatic(Clock.class)
                .when(Clock::systemDefaultZone)
                .thenReturn(clock);
    }

    @Test
    public void getAgeTestNullParam() throws Exception {
        //given

        //when

        //then
        assertThat(calculationAge.getAge(null)).isEqualTo(-1);
    }


    @Test
    public void getAgeTestBirthDateLessThanOneYear() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2022, 1, 1);

        //when

        //then
        assertThat(calculationAge.getAge(birthDate)).isEqualTo(0);
    }

    @Test
    public void getAgeTestBirthDateLateThanNow() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2022, 1, 15);

        //when

        //then
        assertThat(calculationAge.getAge(birthDate)).isEqualTo(-1);
    }

    @Test
    public void getAgeTestBirthDateEqualTenYear() throws Exception {
        //given
        LocalDate birthDate = LocalDate.of(2012, 7, 1);

        //when

        //then
        assertThat(calculationAge.getAge(birthDate)).isEqualTo(10);
    }
}
