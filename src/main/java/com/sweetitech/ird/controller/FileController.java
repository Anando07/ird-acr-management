package com.sweetitech.ird.controller;

import com.sweetitech.ird.common.Util.MediaTypeUtils;
import com.sweetitech.ird.domain.AcrFile;
import com.sweetitech.ird.service.AcrFileService;
import com.sweetitech.ird.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/26/18 at 12:58 PM
 * @project ird
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.download.base}")
    private String DOWNLOAD_FOLDER;

    @Autowired
    private ServletContext servletContext;

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Value("${file.upload.url}")
    private String UPLOADED_FOLDER;

    @Autowired
    AcrFileService imageService;



    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile2(
            @RequestParam(value = "fileName") String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        try {
            Path path = Paths.get(UPLOADED_FOLDER + "/" + fileName);
            byte[] data = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity.ok()

                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + path.getFileName().toString())
                    .contentType(mediaType)
                    .contentLength(data.length)
                    .body(resource);
        } catch (NoSuchFileException e) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
        }
    }






    @PostMapping("/upload")
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

            String url = DOWNLOAD_FOLDER+"/"+ fileo.getFilename();
            System.out.println("demo download url when u add the base url: " + url);

            System.out.println(fileo.getFilename());

            AcrFile image = imageService.addImage(new AcrFile(fileo.getFilename()));
            System.out.println("value of images is "+ image.toString());

            imagesToBeReturned.add(imageService.findByUrl(fileo.getFilename()));

        }
        return imagesToBeReturned;
    }
}
