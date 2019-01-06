package com.sweetitech.ird.mapper.responseTorequest;

import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.AcrFileRelation;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.service.AcrFileRelService;
import com.sweetitech.ird.service.AcrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/31/18 at 1:10 PM
 * @project ird
 */
@Service
public class AcrResponseToRequestMapper {

    @Autowired
    AcrFileRelService fileRelService;

    @Autowired
    AcrFileService fileService;




    public AcrRequestDto map(AcrResponseDto dto)
    {
        AcrRequestDto requestDto = new AcrRequestDto();

        requestDto.setId(dto.getId());
        requestDto.setGovtId(dto.getGovtId());
        requestDto.setYear(dto.getYear());
        requestDto.setAssigned_from(dto.getAssigned_from());
        requestDto.setAssigned_to(dto.getAssigned_to());

        List<AcrFileRelation> relList = fileRelService.findObjByAcrId(dto.getId());

        List<AcrFile> fileList = new ArrayList<>();

        for(AcrFileRelation rel : relList)
        {
            fileList.add(fileService.findById(rel.getAcrFile().getId()));
        }
        System.out.println("file list is "+ fileList.toString());
        requestDto.setAcrFiles(fileList);
        return requestDto;
    }
}
