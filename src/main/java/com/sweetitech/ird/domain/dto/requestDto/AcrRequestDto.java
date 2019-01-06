package com.sweetitech.ird.domain.dto.requestDto;

import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 1:32 PM
 * @project ird
 */

@Validated
public class AcrRequestDto {

    private Long id;

    @NotNull
    private String govtId;

    private String year;

    @NotNull
    private String assigned_from;

    @NotNull
    private String assigned_to;

    private UserResponseDto userResponseDto;

    List<Long> fileList = new ArrayList<>();

    List<AcrFile> acrFiles = new ArrayList<>();


    public AcrRequestDto() {
    }

    public String getGovtId() {
        return govtId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getFileList() {
        return fileList;
    }

    public void setFileList(List<Long> fileList) {
        this.fileList = fileList;
    }

    public List<AcrFile> getAcrFiles() {
        return acrFiles;
    }

    public void setAcrFiles(List<AcrFile> acrFiles) {
        this.acrFiles = acrFiles;
    }

    @Override
    public String toString() {
        return "AcrRequestDto{" +
                "id=" + id +
                ", govtId='" + govtId + '\'' +
                ", year='" + year + '\'' +
                ", assigned_from='" + assigned_from + '\'' +
                ", assigned_to='" + assigned_to + '\'' +
                ", userResponseDto=" + userResponseDto +
                ", fileList=" + fileList +
                '}';
    }
}
