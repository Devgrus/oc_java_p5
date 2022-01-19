package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.FireDto;
import com.safetynet.alerts.model.dto.ResidentDto;
import com.safetynet.alerts.service.FireService;
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

@WebMvcTest(FireController.class)
public class FireControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FireService fireService;

    @Test
    public void getResidentListTest() throws Exception {
        //given
        List<ResidentDto> residentDtoList = new ArrayList<>();
        residentDtoList.add(new ResidentDto("Aee", "111-111-1111", 20, List.of("a", "b"), List.of("z")));
        residentDtoList.add(new ResidentDto("Aee", "111-111-2222", 20, List.of("a", "b"), List.of("z")));
        FireDto fireDto = new FireDto(1, residentDtoList);

        //when
        when(fireService.getResidentList("10 aaa")).thenReturn(fireDto);

        //then
        mockMvc.perform(get("/fire")
                .param("address", "10 aaa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        
    }

    @Test
    public void getResidentListTestWithNoResult() throws Exception {
        //given

        //when
        when(fireService.getResidentList("10 aaa")).thenReturn(null);

        //then
        mockMvc.perform(get("/fire")
                        .param("address", "10 aaa")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }
}
