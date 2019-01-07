package com.sweetitech.ird.service;

import com.sweetitech.ird.domain.AcrFile;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 1:05 PM
 * @project ird
 */

public interface AcrFileService {

    AcrFile addImage(AcrFile image);

    void deleteImage(AcrFile image);

    AcrFile findById(Long id);

    AcrFile findByUrl(String url);

    AcrFile updateImage(AcrFile image);

    List<AcrFile> filesOfSingleAcr(Long acrId);

    void deleteFile(Long fileId);
}
