package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.repository.AcrFileRepository;
import com.sweetitech.ird.service.AcrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Override
    public AcrFile addImage(AcrFile image) {

        AcrFile i =findByUrl(image.getUrl());
        if(i==null) {
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
}
