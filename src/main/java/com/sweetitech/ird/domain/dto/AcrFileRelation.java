package com.sweetitech.ird.domain.dto;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.AcrFile;

import javax.persistence.*;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 12:31 PM
 * @project ird
 */
@Entity
@Table(name = "relation")
public class AcrFileRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private ACR acr;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private AcrFile acrFile;

    public AcrFileRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ACR getAcr() {
        return acr;
    }

    public void setAcr(ACR acr) {
        this.acr = acr;
    }

    public AcrFile getAcrFile() {
        return acrFile;
    }

    public void setAcrFile(AcrFile acrFile) {
        this.acrFile = acrFile;
    }
}