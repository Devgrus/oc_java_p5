package com.safetynet.alerts.model.dto;

import java.util.List;

public class FireStationCommunityDto {

    private List<CommunityMemberDto> communityMemberDtoList;
    private int adultCount;
    private int childCount;

    public FireStationCommunityDto() {}

    public FireStationCommunityDto(List<CommunityMemberDto> communityMemberDtoList, int adultCount, int childCount) {
        this.communityMemberDtoList = communityMemberDtoList;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }


    public List<CommunityMemberDto> getCommunityMemberDtoList() {
        return communityMemberDtoList;
    }

    public void setCommunityMemberDtoList(List<CommunityMemberDto> communityMemberDtoList) {
        this.communityMemberDtoList = communityMemberDtoList;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
