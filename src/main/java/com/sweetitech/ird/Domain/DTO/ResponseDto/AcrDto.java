package com.sweetitech.ird.Domain.DTO.ResponseDto;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 6:00 PM
 * @project InternalResourcesDivision
 */
public class AcrDto {

    private Long id;

    private String govtId;

    private String year;

    private String assigned_from;

    private String assigned_to;

    private List<String> fileUrl;

    public AcrDto() {
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

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }
}
