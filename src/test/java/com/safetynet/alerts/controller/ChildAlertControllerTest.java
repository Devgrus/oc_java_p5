package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.ChildAlertDto;
import com.safetynet.alerts.model.dto.FamilyMemberDto;
import com.safetynet.alerts.service.ChildAlertService;
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

@WebMvcTest(ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChildAlertService childAlertService;

    @Test
    public void getChildrenListByAddressTest() throws Exception {
        //given
        List<FamilyMemberDto> familyMemberDtoList = new ArrayList<>();
        familyMemberDtoList.add(new FamilyMemberDto("Cc", "Aee", "111-111-1111", "ccc@abc.com"));
        familyMemberDtoList.add(new FamilyMemberDto("Dd", "Aee", "111-111-2222", "eee@abc.com"));

        List<ChildAlertDto> childAlertDtoList = new ArrayList<>();
        childAlertDtoList.add(new ChildAlertDto("Aa", "Aee", 10, familyMemberDtoList));

        //when
        when(childAlertService.getChildrenList(anyString())).thenReturn(childAlertDtoList);

        //then
        mockMvc.perform(get("/childAlert")
                .param("address", "10 aaa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void getChildrenListByAddressTestWithNoResult() throws Exception {
        //given

        //when
        when(childAlertService.getChildrenList(anyString())).thenReturn(null);

        //then
        mockMvc.perform(get("/childAlert")
                        .param("address", "10 aaa")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
