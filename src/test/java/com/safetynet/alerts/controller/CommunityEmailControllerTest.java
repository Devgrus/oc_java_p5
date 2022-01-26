package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.EmailDto;
import com.safetynet.alerts.service.CommunityEmailService;
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

@WebMvcTest(CommunityEmailController.class)
public class CommunityEmailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommunityEmailService communityEmailService;

    @Test
    public void getEmailListTest() throws Exception {
        //given
        List<String> emailList = new ArrayList<>();
        emailList.add("aaa@abc.com");
        emailList.add("bbb@abc.com");
        emailList.add("ccc@abc.com");
        emailList.add("ddd@abc.com");

        //when
        when(communityEmailService.getEmailList(anyString())).thenReturn(new EmailDto(emailList));

        //then
        mockMvc.perform(get("/communityEmail")
                .param("city", "aaa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void getEmailListTestWithNoResult() throws Exception {
        //given

        //when
        when(communityEmailService.getEmailList(anyString())).thenReturn(null);

        //then
        mockMvc.perform(get("/communityEmail")
                        .param("city", "aaa")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
