package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.PhoneAlertDto;
import com.safetynet.alerts.service.PhoneAlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(PhoneAlertController.class)
public class PhoneAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PhoneAlertService phoneAlertService;

    @Test
    public void getPhoneListByStationNumberTest() throws Exception {
        //given

        List<PhoneAlertDto> phoneAlertDtoList = new ArrayList<>();
        phoneAlertDtoList.add(new PhoneAlertDto("111-111-1111"));
        phoneAlertDtoList.add(new PhoneAlertDto("111-111-2222"));

        //when
        when(phoneAlertService.getPhoneList(anyInt())).thenReturn(phoneAlertDtoList);

        //then
        mockMvc.perform(get("/phoneAlert")
                .param("firestation", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void getPhoneListByStationNumberTestWithNoResult() throws Exception {
        //given

        //when
        when(phoneAlertService.getPhoneList(anyInt())).thenReturn(null);

        //then
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

}
