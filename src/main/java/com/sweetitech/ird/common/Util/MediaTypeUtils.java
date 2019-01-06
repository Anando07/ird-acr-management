package com.sweetitech.ird.common.Util;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
/**
 * @author Avijit Barua
 * @created_on 1/6/19 at 2:15 PM
 * @project ird
 */
public class MediaTypeUtils {

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

}