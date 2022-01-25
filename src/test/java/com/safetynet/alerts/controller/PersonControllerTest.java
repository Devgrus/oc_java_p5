package com.safetynet.alerts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PersonService personService;


    @Test
    public void saveTest() throws Exception {
        //given

        //when
        when(personService.save(any())).thenReturn(true);

        //then
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"))))
                .andExpect(status().is(201));
    }

    @Test
    public void saveTestBadRequest() throws Exception {
        //given

        //when
        when(personService.save(any())).thenReturn(false);

        //then
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"))))
                .andExpect(status().is(400));
    }

    @Test
    public void updateTest() throws Exception {
        //given

        //when
        when(personService.update(any())).thenReturn(true);

        //then
        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"))))
                .andExpect(status().is(200));
    }

    @Test
    public void updateTestBadRequest() throws Exception {
        //given

        //when
        when(personService.update(any())).thenReturn(false);

        //then
        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Person("Am", "Aen", "10 aaa", "aaa", 10000, "111-111-1111", "aaa@abc.com"))))
                .andExpect(status().is(400));
    }

    @Test
    public void deleteTest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "Aa");
        params.add("lastName", "Aen");

        //when
        when(personService.delete(any(), any())).thenReturn(true);

        //then
        mockMvc.perform(delete("/person")
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
        when(personService.delete(any(), any())).thenReturn(false);

        //then
        mockMvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params))
                .andExpect(status().is(400));
    }
}
