package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MedicalRecordService medicalRecordService;

    @Test
    public void saveTest() throws Exception {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordService.save(any())).thenReturn(true);

        //then
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().is(201));
    }

    @Test
    public void saveTestBadRequest() throws Exception {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordService.save(any())).thenReturn(false);

        //then
        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().is(400));
    }

    @Test
    public void updateTest() throws Exception {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordService.update(any())).thenReturn(true);

        //then
        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().is(200));
    }

    @Test
    public void updateTestBadRequest() throws Exception {
        //given
        MedicalRecord medicalRecord = new MedicalRecord("Am", "Aen", LocalDate.of(1990, 4, 12), List.of("a", "b"), List.of("z"));

        //when
        when(medicalRecordService.update(any())).thenReturn(false);

        //then
        mockMvc.perform(put("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().is(400));
    }

    @Test
    public void deleteTest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "Aa");
        params.add("lastName", "Aen");

        //when
        when(medicalRecordService.delete(any(), any())).thenReturn(true);

        //then
        mockMvc.perform(delete("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteTestBadRequest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "Aa");
        params.add("lastName", "Aen");

        //when
        when(medicalRecordService.delete(any(), any())).thenReturn(false);

        //then
        mockMvc.perform(delete("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params))
                .andExpect(status().is(400));
    }
}
