package com.sweetitech.ird.domain.dto.requestDto;

import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 1:32 PM
 * @project ird
 */
public class AcrRequestDto {

    private String govtId;

    private String year;

    private String assigned_from;

    private String assigned_to;

    private UserResponseDto userResponseDto;

    List<Integer> fileList = new ArrayList<>();


    public AcrRequestDto() {
    }

    public String getGovtId() {
        return govtId;
    }

    public void setGovtId(String govtId) {
        this.govtId = govtId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAssigned_from() {
        return assigned_from;
    }

    public void setAssigned_from(String assigned_from) {
        this.assigned_from = assigned_from;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public List<Integer> getFileList() {
        return fileList;
    }

    public void setFileList(List<Integer> fileList) {
        this.fileList = fileList;
    }



    @Override
    public String toString() {
        return "AcrRequestDto{" +
                "govtId='" + govtId + '\'' +
                ", year='" + year + '\'' +
                ", assigned_from='" + assigned_from + '\'' +
                ", assigned_to='" + assigned_to + '\'' +
                ", userResponseDto=" + userResponseDto +
                ", fileList=" + fileList +
                '}';
    }
}
