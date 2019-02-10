package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.domain.ACR;
import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.domain.AcrFileRelation;
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
        AcrFileRelation obj = new AcrFileRelation();
        obj.setAcr(acr);
        obj.setAcrFile(file);
        return repository.save(obj);
    }

    @Override
    public List<AcrFileRelation> findListByAcrId(Long acrId) {

        return repository.findByAcr_Id(acrId);
    }

    @Override
    public AcrFileRelation getRelByFileId(Long fileId) {
        return repository.findAcrFileRelationByAcrFile_Id(fileId);
    }

    @Override
    public void deleteRelByFileId(Long fileId) {
        AcrFileRelation rel = repository.findAcrFileRelationByAcrFile_Id(fileId);
        repository.delete(rel);
    }
}
