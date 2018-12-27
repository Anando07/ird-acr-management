package com.sweetitech.ird.controller;

import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.service.AcrFileService;
import com.sweetitech.ird.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 12:58 PM
 * @project ird
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Value("${file.download.base}")
    private String DOWNLOAD_FOLDER;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Value("${file.upload.url}")
    private String UPLOADED_FOLDER;

    @Autowired
    AcrFileService imageService;




    @PostMapping("/file")
    public ResponseEntity<List<AcrFile>> uploadFile(@RequestParam("file")MultipartFile[] files) {

        List<AcrFile> list = new ArrayList<>();

        if(!files[0].isEmpty()) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    return new ResponseEntity("please select a file!", HttpStatus.OK);
                }

                try {
                    AcrFile i = saveUploadedFiles(Arrays.asList(file)).get(0);
                    list.add(i);
                } catch (IOException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } catch (org.springframework.web.multipart.MultipartException eo) {
                    System.out.println("Maximum file size exceeds");
                    return new ResponseEntity("Maximum file size exceeds", HttpStatus.FORBIDDEN);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("just an exception");
                    System.out.println(e.getStackTrace().toString());
                    System.out.println(e.getLocalizedMessage());
                    return new ResponseEntity("Some thing went wrong please try again later", HttpStatus.BAD_GATEWAY);
                }
            }
            return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }




    // save file
    private List<AcrFile> saveUploadedFiles(List<MultipartFile> files) throws IOException {

        List<AcrFile> imagesToBeReturned = new ArrayList<AcrFile>();
        for (MultipartFile file : files) {


            if (file.isEmpty()) {
                continue; // next pls
            }

            String fileName = storageService.store(file);

            Resource fileo = storageService.loadAsResource(fileName);

            System.out.println(fileo.getURL().toString());

            String url = DOWNLOAD_FOLDER+"/"+fileo.getFilename().toString();
            System.out.println("demo download url when u add the base url: " + url);

            System.out.println(fileo.getFilename());

            AcrFile image = imageService.addImage(new AcrFile(fileo.getFilename()));
            System.out.println("value of image is "+ image.toString());

            imagesToBeReturned.add(imageService.findByUrl(fileo.getFilename()));

        }
        return imagesToBeReturned;
    }
}
