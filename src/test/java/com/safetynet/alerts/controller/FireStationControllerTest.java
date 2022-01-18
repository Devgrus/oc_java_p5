package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.dto.CommunityMemberDto;
import com.safetynet.alerts.model.dto.FireStationCommunityDto;
import com.safetynet.alerts.service.FireStationCommunityService;
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
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest
public class FireStationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FireStationCommunityService fireStationCommunityService;

    @Test
    public void getFireStationCommunityByStationNumberTest() throws Exception {
        //given
        List<CommunityMemberDto> communityMemberDtoList = new ArrayList<>();
        communityMemberDtoList.add(new CommunityMemberDto("Aaa", "Arr","1 road", "111-111-1111"));
        communityMemberDtoList.add(new CommunityMemberDto("Bbb", "Arr","2 road", "111-111-2222"));
        communityMemberDtoList.add(new CommunityMemberDto("Ccc", "Arr","4 road", "111-111-3333"));
        FireStationCommunityDto fireStationCommunityDto = new FireStationCommunityDto(communityMemberDtoList, 1, 2);

        //when
        when(fireStationCommunityService.getFireStationCommunity(anyInt())).thenReturn(fireStationCommunityDto);

        //then
        mockMvc.perform(get("/firestation")
                .param("stationNumber", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void getFireStationCommunityByStationNumberTestWithNoResult() throws Exception {
        //given

        //when
        when(fireStationCommunityService.getFireStationCommunity(anyInt())).thenReturn(null);

        //then
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
