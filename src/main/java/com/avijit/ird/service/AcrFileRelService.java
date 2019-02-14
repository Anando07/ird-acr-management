package com.avijit.ird.service;

import com.avijit.ird.domain.ACR;
import com.avijit.ird.domain.AcrFileRelation;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:59 AM
 * @project ird
 */
public interface AcrFileRelService {

    AcrFileRelation saveRelation(ACR acr, Long fileId);

    List<AcrFileRelation> findListByAcrId(Long acrId);

    AcrFileRelation getRelByFileId(Long fileId);

    void deleteRelByFileId(Long fileId);
}
