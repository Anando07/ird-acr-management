package com.avijit.ird.serviceImpl;

import com.avijit.ird.common.LogStatus;
import com.avijit.ird.common.Util.DateUtil;
import com.avijit.ird.configuration.MySessionInfo;
import com.avijit.ird.domain.AuditLog;
import com.avijit.ird.domain.dto.AcrDTO;
import com.avijit.ird.mapper.AcrMapper;
import com.avijit.ird.repository.AuditLogRepository;
import com.avijit.ird.service.AcrFileRelService;
import com.avijit.ird.domain.ACR;
import com.avijit.ird.repository.AcrRepository;
import com.avijit.ird.service.AcrService;
import com.avijit.ird.service.UserService;
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
    AuditLogRepository logRepository;

    @Autowired
    MySessionInfo sessionInfo;


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
        //saving log at log table
        if (acrRequestDto.getId() == null) {
            logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.createAcr+ new Date()));
        } else {
            logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.updateAcr+ new Date()));
        }
        return acr;
    }


    @Override
    public List<AcrDTO> acrOfCurrentYear() {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        List<ACR> list = acrRepository.findByYearOrderByCreatedOnDesc(date[2]);
        /*List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR obj : list) {
            if (!obj.getDeleted() || obj.getDeleted() == null) {
                dtoList.add(acrMapper.responseMapper(obj));
            }
        }
        return dtoList;*/
        return this.acrEntityToDTO(list);
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
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.deleteAcr+ new Date()));
    }

    @Override
    public List<AcrDTO> getAcrOfGovtIdWithCurrentYear(String govtId) {
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");
        List<ACR> acrList = acrRepository.findRequiredACRbyGovtIdAndYear(govtId, date[2]);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }

    @Override
    public List<AcrDTO> getAcrByYear(String year) {
        List<ACR> acrList = acrRepository.findByYearOrderByCreatedOnDesc(year);
        /*List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            if (!acr.getDeleted()) {
                dtoList.add(acrMapper.responseMapper(acr));
            }
        }
        return dtoList;*/
        return this.acrEntityToDTO(acrList);
    }

    @Override
    public List<AcrDTO> getAcrByDeptId(Long deptId) {
        List<ACR> acrList = acrRepository.findAllByDepartmentIdOrderByYearDesc(deptId);
        /*List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            if (!acr.getDeleted()) {
                dtoList.add(acrMapper.responseMapper(acr));
            }
        }
        return dtoList;*/
        return this.acrEntityToDTO(acrList);
    }

    @Override
    public List<AcrDTO> getAcrByYearAndDeptId(String year, Long deptId) {
        List<ACR> acrList = acrRepository.findAllByYearAndDepartmentIdOrderByGovtId(year, deptId);
/*        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            if (!acr.getDeleted()) {
                dtoList.add(acrMapper.responseMapper(acr));
            }
        }
        return dtoList;*/
        return this.acrEntityToDTO(acrList);
    }


    @Override
    public List<AcrDTO> getAllNotRequiredAcrByGovtId(String govtId) {
        List<ACR> acrList = acrRepository.findAllByGovtIdAndAcrRequiredTypeFalseAndIsDeletedFalseOrderByYearDesc(govtId);
/*        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            if (!acr.getDeleted()) {
                dtoList.add(acrMapper.responseMapper(acr));
            }
        }
        return dtoList;*/
        return this.acrEntityToDTO(acrList);
    }

    //this method was created to reduce redundant code in several method!
    private List<AcrDTO> acrEntityToDTO(List<ACR> acrList)
    {
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            if (!acr.getDeleted() || acr.getDeleted() ==null) {
                dtoList.add(acrMapper.responseMapper(acr));
            }
        }
        return dtoList;
    }

    @Override
    public List<AcrDTO> getAllAcrByGovtId(String govtId) {
        List<ACR> acrList = acrRepository.findByGovtIdOrderByYearDesc(govtId);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }


    @Override
    public List<AcrDTO> getAllRequiredAcrByGovtId(String govtId) {
        List<ACR> acrList = acrRepository.findAllRequiredACRbyGovtId(govtId);
        List<AcrDTO> dtoList = new ArrayList<>();
        for (ACR acr : acrList) {
            dtoList.add(acrMapper.responseMapper(acr));
        }
        return dtoList;
    }

    @Override
    public List<AcrDTO> getAcrList(String year, String deptId) {

        if (year.isEmpty() && deptId.isEmpty()) {
            return this.acrOfCurrentYear();
        } else if (!year.isEmpty() && deptId.isEmpty()) {
            return this.getAcrByYear(year);
        } else if (year.isEmpty() && !deptId.isEmpty()) {
            return this.getAcrByDeptId(Long.parseLong(deptId));
        } else {
            return this.getAcrByYearAndDeptId(year, Long.parseLong(deptId));
        }

    }
}


//not used method
/*
    @Override
    public AcrDTO updateAcr(AcrDTO acrRequestDto) throws ParseException {

        ACR acr = acrMapper.requestMapper(acrRequestDto);
        acrRepository.save(acr);
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName()+LogStatus.updateAcr));
        for (Long l : acrRequestDto.getFileList()) {
            acrFileRelService.saveRelation(acr, l);
        }
        return acrMapper.responseMapper(acr);
    }
*/


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
