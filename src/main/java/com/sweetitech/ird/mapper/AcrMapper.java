package com.sweetitech.ird.mapper;

import com.sweetitech.ird.common.Util.DateTimeUtils;
import com.sweetitech.ird.configuration.MySessionInfo;
import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.AcrFileRelation;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.AcrDTO;
import com.sweetitech.ird.service.AcrFileRelService;
import com.sweetitech.ird.service.AcrFileService;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 2/10/19 at 3:16 PM
 * @project ird
 */

@Service
public class AcrMapper {


    @Autowired
    AcrFileRelService fileRelService;

    @Autowired
    AcrFileService acrFileService;

    @Value("${base.url.images}")
    String fileDownloadUrl;

    @Autowired
    UserService userService;

    @Autowired
    MySessionInfo sessionInfo;

    public ACR requestMapper(AcrDTO dto) throws ParseException {
        ACR acr= new ACR();
        if(dto.getId() !=null)
        {
            acr.setId(dto.getId());
        }
        acr.setGovtId(dto.getGovtId());
        acr.setName(dto.getName());
        acr.setComment1(dto.getComment1());
        acr.setComment2(dto.getComment2());
        acr.setMarks(dto.getMarks());
        acr.setYear(dto.getYear());
        acr.setAssigned_from(DateTimeUtils.toDate(dto.getAssigned_from()));
        acr.setAssigned_to(DateTimeUtils.toDate(dto.getAssigned_to()));
        User user = sessionInfo.getCurrentUser();
        acr.setUser(user);
        acr.setCreatedOn(new Date());
        return acr;
    }


    public AcrDTO responseMapper(ACR acr)
    {
        AcrDTO dto = new AcrDTO();
        dto.setId(acr.getId());
        dto.setGovtId(acr.getGovtId());
        dto.setName(acr.getName());
        dto.setComment1(acr.getComment1());
        dto.setComment2(acr.getComment2());
        dto.setMarks(acr.getMarks());
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
        dto.setFileNameList(fileList);
        return dto;
    }
}
