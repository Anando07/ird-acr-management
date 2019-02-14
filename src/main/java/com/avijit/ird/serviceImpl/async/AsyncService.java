package com.avijit.ird.serviceImpl.async;

import com.avijit.ird.domain.AcrFile;
import com.avijit.ird.service.AcrFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Avijit Barua
 * @created_on 1/7/19 at 2:52 PM
 * @project ird
 */
@Service
public class AsyncService {

    @Value("${file.upload.url}")
    private String UPLOADED_FOLDER;

    @Autowired
    AcrFileService fileService;

    @Async
    public void deleteFileFromStorage(Long fileId)
    {
        AcrFile acrFile = fileService.findById(fileId);

        try{
            System.out.println("Delete filepath from AJX");
            File file = new File(UPLOADED_FOLDER+"/"+acrFile.getUrl());

            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");

            }else{
                System.out.println("Delete operation is failed.");
            }

        }catch(Exception e){

            e.printStackTrace();

        }
    }
}
