package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FloodDto;
import com.safetynet.alerts.model.dto.ResidentDto;
import com.safetynet.alerts.service.FloodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(FloodController.class)
public class FloodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FloodService floodService;

    @Test
    public void getResidentListByStationsNumbersTest() throws Exception {
        //given
        List<ResidentDto> residentDtoList1 = new ArrayList<>();
        residentDtoList1.add(new ResidentDto("Aee", "111-111-1111", 20, List.of("a", "b"), List.of("z")));
        residentDtoList1.add(new ResidentDto("Aee", "111-111-2222", 40, List.of("a", "b"), List.of("z")));

        List<ResidentDto> residentDtoList2 = new ArrayList<>();
        residentDtoList2.add(new ResidentDto("Bee", "111-111-1111", 30, List.of("a", "b"), List.of("z")));
        residentDtoList2.add(new ResidentDto("Cee", "111-111-2222", 60, List.of("a", "b"), List.of("z")));

        List<FloodDto> floodDtoList = new ArrayList<>();
        floodDtoList.add(new FloodDto("10 aaa", residentDtoList1));
        floodDtoList.add(new FloodDto("20 bbb", residentDtoList2));

        //when
        when(floodService.getResidentListByStationsNumbers(List.of(1, 2))).thenReturn(floodDtoList);

        //then
        mockMvc.perform(get("/flood/stations")
                .param("stations", "1", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void getResidentListByStationsNumbersTestWithNoResult() throws Exception {
        //given

        //when
        when(floodService.getResidentListByStationsNumbers(List.of(1, 2))).thenReturn(new ArrayList<>());

        //then
        mockMvc.perform(get("/flood/stations")
                        .param("stations", "1", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
