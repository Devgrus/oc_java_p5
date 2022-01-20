package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PersonInfoDto;
import com.safetynet.alerts.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonInfoService personInfoService;

    @Test
    public void getPersonInfoListTest() throws Exception {
        //given
        List<PersonInfoDto> personInfoDtoList = new ArrayList<>();
        personInfoDtoList.add(new PersonInfoDto("Aen", "10 aaa", 20, "aaa@abc.com", List.of("a", "b"), List.of("z")));
        personInfoDtoList.add(new PersonInfoDto("Aen", "10 aaa", 10, "bbb@abc.com", List.of("a", "b"), List.of("z")));
        personInfoDtoList.add(new PersonInfoDto("Aen", "10 aaa", 30, "ccc@abc.com", List.of("a", "b"), List.of("z")));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "Aa");
        params.add("lastName", "Aen");

        //when
        when(personInfoService.getPersonInfoList(anyString())).thenReturn(personInfoDtoList);

        //then
        mockMvc.perform(get("/personInfo")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }

    @Test
    public void getPersonInfoListTestWithNoResult() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("firstName", "Aa");
        params.add("lastName", "Aen");

        //when
        when(personInfoService.getPersonInfoList(anyString())).thenReturn(null);

        //then
        mockMvc.perform(get("/personInfo")
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }
}
