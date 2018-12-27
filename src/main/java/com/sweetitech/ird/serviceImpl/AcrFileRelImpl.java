package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.dto.AcrFileRelation;
import com.sweetitech.ird.repository.AcrFileRelationRepository;
import com.sweetitech.ird.repository.AcrFileRepository;
import com.sweetitech.ird.service.AcrFileRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/27/18 at 11:01 AM
 * @project ird
 */
@Service
public class AcrFileRelImpl implements AcrFileRelService {

    AcrFileRelationRepository repository;

    @Autowired
    AcrFileRepository acrFileRepository;

    @Autowired
    public AcrFileRelImpl(AcrFileRelationRepository repository) {
        this.repository = repository;
    }


    @Override
    public AcrFileRelation saveRelation(ACR acr, Long fileId) {
        AcrFile file = acrFileRepository.getFirstById(fileId);
        file.toString();
        AcrFileRelation obj = new AcrFileRelation();
        obj.setAcr(acr);
        obj.setAcrFile(file);
        return repository.save(obj);
    }

    @Override
    public List<AcrFileRelation> findObjByAcrId(Long acrId) {

        return repository.findByAcr_Id(acrId);
    }
}
