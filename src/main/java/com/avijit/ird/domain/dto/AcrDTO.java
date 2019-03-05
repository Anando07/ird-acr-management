package com.avijit.ird.domain.dto;

import com.avijit.ird.domain.AcrFile;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 2/10/19 at 4:07 PM
 * @project ird
 */
@Validated
public class AcrDTO {

    private Long id;

    @NotNull
    private String govtId;

    private String name;

    private String comment1;

    private String comment2;

    private Double marks;

    private String year;

    private Long deptId;

    @NotNull
    private String assigned_from;

    @NotNull
    private String assigned_to;

    private UserDTO userResponseDto;

    private List<Long> fileList = new ArrayList<>();

    private List<String> fileNameList;

    private List<AcrFile> acrFiles;

    public AcrDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGovtId() {
        return govtId;
    }

    public void setGovtId(String govtId) {
        this.govtId = govtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
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

    public UserDTO getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserDTO userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public List<Long> getFileList() {
        return fileList;
    }

    public void setFileList(List<Long> fileList) {
        this.fileList = fileList;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }

    public List<AcrFile> getAcrFiles() {
        return acrFiles;
    }

    public void setAcrFiles(List<AcrFile> acrFiles) {
        this.acrFiles = acrFiles;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "AcrDTO{" +
                "id=" + id +
                ", govtId='" + govtId + '\'' +
                ", name='" + name + '\'' +
                ", comment1='" + comment1 + '\'' +
                ", comment2='" + comment2 + '\'' +
                ", marks=" + marks +
                ", year='" + year + '\'' +
                ", assigned_from='" + assigned_from + '\'' +
                ", assigned_to='" + assigned_to + '\'' +
                ", userResponseDto=" + userResponseDto +
                ", fileList=" + fileList +
                ", fileNames=" + fileNameList +
                '}';
    }
}
