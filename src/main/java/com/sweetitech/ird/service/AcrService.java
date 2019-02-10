package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.dto.AcrDTO;

import java.text.ParseException;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:26 AM
 * @project ird
 */
public interface AcrService {

    ACR saveAcr(AcrDTO acrDTO) throws ParseException;

    //AcrResponsePage acrOfCurrentYear(Integer page);

    List<AcrDTO> acrOfCurrentYear();

    AcrDTO updateAcr(AcrDTO acrDTO) throws ParseException;

    AcrDTO getSingleAcr(Long id);

    void deleteAcr(Long id);

    List<AcrDTO> getAcrOfGovtIdWithCurrentYear(String govtId);

    List<AcrDTO> getAllAcrByGovtId(String govtId);

    List<AcrDTO> getAcrByYear(String year);
}
