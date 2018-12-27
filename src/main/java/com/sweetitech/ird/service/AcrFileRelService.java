package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.dto.AcrFileRelation;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:59 AM
 * @project ird
 */
public interface AcrFileRelService {

    AcrFileRelation saveRelation(ACR acr, Long fileId);

    List<AcrFileRelation> findObjByAcrId(Long acrId);
}
