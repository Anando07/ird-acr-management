package com.sweetitech.ird.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 4:01 PM
 * @project InternalResourcesDivision
 */

@Entity
public class ACR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String govtId;

    private String year;

    private Date assigned_from;

    private Date assigned_to;

    @NotNull
    @OneToOne
    User user;

    public ACR() {
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

    public Date getAssigned_from() {
        return assigned_from;
    }

    public void setAssigned_from(Date assigned_from) {
        this.assigned_from = assigned_from;
    }

    public Date getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(Date assigned_to) {
        this.assigned_to = assigned_to;
    }


}
