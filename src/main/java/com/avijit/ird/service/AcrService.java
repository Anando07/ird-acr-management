package com.avijit.ird.service;

import com.avijit.ird.domain.dto.AcrDTO;
import com.avijit.ird.domain.ACR;

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

/*    AcrDTO updateAcr(AcrDTO acrDTO) throws ParseException;*/

    AcrDTO getSingleAcr(Long id);

    void deleteAcr(Long id);

    List<AcrDTO> getAcrOfGovtIdWithCurrentYear(String govtId);

    List<AcrDTO> getAllAcrByGovtId(String govtId);

    List<AcrDTO> getAllRequiredAcrByGovtId(String govtId);

    List<AcrDTO> getAcrByYear(String year);

    List<AcrDTO> getAcrList(String year, String deptId);

    List<AcrDTO> getAcrByDeptId(Long deptId);

    List<AcrDTO> getAcrByYearAndDeptId(String year, Long deptId);

    List<AcrDTO> getAllNotRequiredAcrByGovtId(String govtId);
}
