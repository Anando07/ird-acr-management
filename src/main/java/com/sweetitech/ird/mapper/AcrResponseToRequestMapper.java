package com.sweetitech.ird.mapper;

import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author Avijit Barua
 * @created_on 12/31/18 at 1:10 PM
 * @project ird
 */
@Service
public class AcrResponseToRequestMapper {
    public AcrRequestDto map(AcrResponseDto dto)
    {
        AcrRequestDto requestDto = new AcrRequestDto();

        requestDto.setId(dto.getId());
        requestDto.setGovtId(dto.getGovtId());
        requestDto.setYear(dto.getYear());
        requestDto.setAssigned_from(dto.getAssigned_from());
        requestDto.setAssigned_to(dto.getAssigned_to());
        return requestDto;
    }
}
