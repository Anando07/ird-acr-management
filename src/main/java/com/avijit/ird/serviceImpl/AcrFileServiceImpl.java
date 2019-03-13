package com.avijit.ird.serviceImpl;

import com.avijit.ird.domain.AcrFile;
import com.avijit.ird.domain.AcrFileRelation;
import com.avijit.ird.repository.AcrFileRepository;
import com.avijit.ird.service.AcrFileRelService;
import com.avijit.ird.service.AcrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 1:07 PM
 * @project ird
 */
@Service
@Transactional
public class AcrFileServiceImpl implements AcrFileService {


    @Autowired
    AcrFileRepository acrFileRepository;

    @Autowired
    AcrFileRelService acrFileRelService;

    @Autowired
    AcrFileService acrFileService;

    @Override
    public AcrFile addImage(AcrFile image) {

        AcrFile i = findByUrl(image.getUrl());
        if (i == null) {
            return acrFileRepository.save(image);
        }
        return i;
    }

    @Override
    public void deleteImage(AcrFile image) {

        acrFileRepository.delete(image);
    }

    @Override
    public AcrFile findById(Long id) {

        return acrFileRepository.getFirstById(id);
    }

    @Override
    public AcrFile findByUrl(String url) {

        return acrFileRepository.findByUrl(url);
    }

    @Override
    public AcrFile updateImage(AcrFile image) {
        return acrFileRepository.save(image);
    }

    @Override
    public List<AcrFile> filesOfSingleAcr(Long acrId) {

        List<AcrFileRelation> relationList = acrFileRelService.findListByAcrId(acrId);

        List<AcrFile> files = new ArrayList<>();
        for (AcrFileRelation relation : relationList) {
            files.add(acrFileService.findById(relation.getAcrFile().getId()));
        }
        return files;
    }

    @Override
    public void deleteFile(Long fileId) {
        acrFileRelService.deleteRelByFileId(fileId);
        AcrFile file = acrFileRepository.getOne(fileId);
        acrFileRepository.delete(file);
    }
}
