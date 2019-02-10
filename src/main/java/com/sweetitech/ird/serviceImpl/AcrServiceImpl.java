package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.common.Util.DateUtil;
import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.dto.AcrDTO;
import com.sweetitech.ird.mapper.AcrMapper;
import com.sweetitech.ird.repository.AcrRepository;
import com.sweetitech.ird.service.AcrFileRelService;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    AcrMapper acrMapper;

    @Autowired
    AcrFileRelService acrFileRelService;

    @Autowired
    UserService userService;


    @Autowired
    public AcrServiceImpl(AcrRepository acrRepository) {
        this.acrRepository = acrRepository;
    }

    @Override
    public ACR saveAcr(AcrDTO acrRequestDto) throws ParseException {

        ACR acr = acrRepository.save(acrMapper.requestMapper(acrRequestDto));
        for (Long l : acrRequestDto.getFileList()) {
            acrFileRelService.saveRelation(acr, l);
        }
        return acr;
    }

/*    @Override
    public AcrResponsePage acrOfCurrentYear(Integer page) {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        Page<ACR> acrlist = acrRepository.findByYearOrderByCreatedOnDesc(date[2], new PageRequest(page, PageAttribute.PAGE_SIZE));

        List<AcrResponseDto> acrResponseDtos = new ArrayList<>();
        for (ACR acr : acrlist.getContent()) {
            acrResponseDtos.add(acrMapper.responseMapper(acr));
        }
        return new AcrResponsePage(acrResponseDtos, acrlist);
    }*/

    @Override
    public List<AcrDTO> acrOfCurrentYear() {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        List<ACR> list = acrRepository.findByYearOrderByCreatedOnDesc(date[2]);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR obj : list) {
            if (!obj.getDeleted() || obj.getDeleted() == null) {
                dtoList.add(acrMapper.responseMapper(obj));
            }
        }
        return dtoList;
    }


    @Override
    public AcrDTO updateAcr(AcrDTO acrRequestDto) throws ParseException {

        ACR acr = acrMapper.requestMapper(acrRequestDto);
        acrRepository.save(acr);
        for (Long l : acrRequestDto.getFileList()) {
            acrFileRelService.saveRelation(acr, l);
        }
        return acrMapper.responseMapper(acr);
    }

    @Override
    public AcrDTO getSingleAcr(Long id) {
        ACR acr = acrRepository.getOne(id);
        return acrMapper.responseMapper(acr);
    }

    @Override
    public void deleteAcr(Long id) {
        ACR acr = acrRepository.getOne(id);
        acr.setDeleted(true);
        acrRepository.save(acr);
    }

    @Override
    public List<AcrDTO> getAcrOfGovtIdWithCurrentYear(String govtId) {

        String[] date = DateUtil.getReadableDate(new Date()).split(" ");

        List<ACR> acrList = acrRepository.findByGovtIdAndYearOrderByCreatedOn(govtId, date[2]);

        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }

    @Override
    public List<AcrDTO> getAllAcrByGovtId(String govtId) {
        List<ACR> acrList = acrRepository.findByGovtId(govtId);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }

    @Override
    public List<AcrDTO> getAcrByYear(String year) {
        List<ACR> acrList = acrRepository.findByYearOrderByCreatedOnDesc(year);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }
}
