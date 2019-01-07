package com.sweetitech.ird.mapper.responseMapper;

import com.sweetitech.ird.common.Util.DateTimeUtils;
import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.AcrFileRelation;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.service.AcrFileRelService;
import com.sweetitech.ird.service.AcrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 4:16 PM
 * @project ird
 */
@Service
public class AcrResponseMapper {

    @Autowired
    AcrFileRelService fileRelService;

    @Autowired
   AcrFileService acrFileService;

    @Value("${base.url.images}")
    String fileDownloadUrl;

    public AcrResponseDto map(ACR acr)
    {
        AcrResponseDto dto = new AcrResponseDto();
        dto.setId(acr.getId());
        dto.setGovtId(acr.getGovtId());
        dto.setYear(acr.getYear());
        dto.setAssigned_from(DateTimeUtils.toDateString(acr.getAssigned_from()));
        dto.setAssigned_to(DateTimeUtils.toDateString(acr.getAssigned_to()));

        List<AcrFileRelation> list = fileRelService.findListByAcrId(acr.getId());

        List<String> fileList = new ArrayList<>();

        for(AcrFileRelation acrFile : list)
        {
            AcrFile file = acrFileService.findById(acrFile.getAcrFile().getId());
            fileList.add(file.getUrl());
        }
        dto.setFilelist(fileList);
        return dto;
    }
}
