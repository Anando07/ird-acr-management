package com.sweetitech.ird.domain.dto.responseDto;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 6:00 PM
 * @project InternalResourcesDivision
 */
public class AcrResponseDto {

    private Long id;

    private String govtId;

    private String year;

    private String assigned_from;

    private String assigned_to;

    private List<String> filelist;

    public AcrResponseDto() {
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

    public List<String> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<String> filelist) {
        this.filelist = filelist;
    }


    @Override
    public String toString() {
        return "AcrResponseDto{" +
                "id=" + id +
                ", govtId='" + govtId + '\'' +
                ", year='" + year + '\'' +
                ", assigned_from='" + assigned_from + '\'' +
                ", assigned_to='" + assigned_to + '\'' +
                ", fileUrl=" +  +
                '}';
    }
}
