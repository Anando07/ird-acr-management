/*
package com.sweetitech.ird.controller;

import com.sweetitech.ird.common.Util.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

*/
/**
 * @author Avijit Barua
 * @created_on 1/6/19 at 2:17 PM
 * @project ird
 *//*

@Controller
@RequestMapping(value = "/file")
public class FileDownloadController {

    @Value("${file.upload.url}")
    private String DIRECTORY;


    @Autowired
    private ServletContext servletContext;

*/
/*    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileName") String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(DIRECTORY + "/" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }*//*


    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile2(
            @RequestParam(value = "fileName") String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        try {
            Path path = Paths.get(DIRECTORY + "/" + fileName);
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
}



*/
