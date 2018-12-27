package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.pageable.AcrResponsePage;

import java.text.ParseException;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:26 AM
 * @project ird
 */
public interface AcrService {

    ACR saveAcr(AcrRequestDto acrRequestDto) throws ParseException;

    AcrResponsePage acrOfCurrentYear(Integer page);

}
