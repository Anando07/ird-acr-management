package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.common.Util.DateTimeUtils;
import com.sweetitech.ird.common.Util.DateUtil;
import com.sweetitech.ird.common.Util.PageAttribute;
import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.mapper.requestMapper.AcrRequestMapper;
import com.sweetitech.ird.mapper.responseMapper.AcrResponseMapper;
import com.sweetitech.ird.pageable.AcrResponsePage;
import com.sweetitech.ird.repository.AcrRepository;
import com.sweetitech.ird.service.AcrFileRelService;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 10:29 AM
 * @project ird
 */

@Service
public class AcrServiceImpl implements AcrService {

    AcrRepository acrRepository;

    @Autowired
    AcrRequestMapper acrRequestMapper;

    @Autowired
    AcrFileRelService acrFileRelService;

    @Autowired
    UserService userService;

    @Autowired
    AcrResponseMapper acrResponseMapper;

    @Autowired
    public AcrServiceImpl(AcrRepository acrRepository) {
        this.acrRepository = acrRepository;
    }

    @Override
    public ACR saveAcr(AcrRequestDto acrRequestDto) throws ParseException {

        ACR acr =  acrRepository.save(acrRequestMapper.map(acrRequestDto));
        for(Long l : acrRequestDto.getFileList())
        {
            acrFileRelService.saveRelation(acr,l);
        }
        return acr;
    }

    @Override
    public AcrResponsePage acrOfCurrentYear(Integer page) {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        Page<ACR> acrlist = acrRepository.findByYearOrderByCreatedOnDesc(date[2],new PageRequest(page, PageAttribute.PAGE_SIZE));

        List<AcrResponseDto> acrResponseDtos = new ArrayList<>();
        for(ACR acr : acrlist.getContent())
        {
            acrResponseDtos.add(acrResponseMapper.map(acr));
        }
        return new AcrResponsePage(acrResponseDtos,acrlist);
    }

    @Override
    public List<AcrResponseDto> acrOfCurrentYear() {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        List<ACR> list = acrRepository.findByYearOrderByCreatedOnDesc(date[2]);
        List<AcrResponseDto> dtoList = new ArrayList<>();
        for(ACR obj : list)
        {
            dtoList.add(acrResponseMapper.map(obj));
        }
        return dtoList;
    }

    @Override
    public List<AcrResponseDto> acrOfOldYear() {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        List<ACR> list = acrRepository.acrOfOldYear(date[2]);
        List<AcrResponseDto> dtoList = new ArrayList<>();
        for(ACR obj : list)
        {
            dtoList.add(acrResponseMapper.map(obj));
        }
        return dtoList;
    }



    @Override
    public AcrResponseDto updateAcr(AcrRequestDto acrRequestDto) throws ParseException {

        System.out.println("inside service "+acrRequestDto.toString());

        ACR acr =  acrRepository.getOne(acrRequestDto.getId());
        acr.setGovtId(acrRequestDto.getGovtId());
        acr.setAssigned_from(DateTimeUtils.toDate(acrRequestDto.getAssigned_from()));
        acr.setAssigned_to(DateTimeUtils.toDate(acrRequestDto.getAssigned_to()));

        User user = userService.findByUserId("ASH1201010M");

        System.out.println(user.toString());
        acr.setUser(user);
        acrRepository.save(acr);

        for(Long l : acrRequestDto.getFileList())
        {
            acrFileRelService.saveRelation(acr,l);
        }

        return acrResponseMapper.map(acr);
    }
}
