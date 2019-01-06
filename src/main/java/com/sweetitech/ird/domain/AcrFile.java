package com.sweetitech.ird.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 4:12 PM
 * @project InternalResourcesDivision
 */

@Entity
@Table(name="acr_files")
public class AcrFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created_on = new Date();

    @Column(columnDefinition = "LONGTEXT")
    private String url;

    public AcrFile() {
    }

    public AcrFile(String filename) {
        this.url = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "AcrFile{" +
                "id=" + id +
                ", created_on=" + created_on +
                ", url='" + url + '\'' +
                '}';
    }
}
