package com.avijit.ird.report;

import com.ztomic.wkhtmltopdf.WkHtmlToPdf;
import com.ztomic.wkhtmltopdf.argument.Argument;
import com.ztomic.wkhtmltopdf.source.Source;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import static com.ztomic.wkhtmltopdf.argument.Option.PageOption.EnableJavascript;


/**
 * @author Avijit Barua
 * @created_on 2/12/19 at 2:15 PM
 * @project ird
 */
public class ReportService {

    //public static final String DOWNLOAD_PATH = "/opt/tomcat/webapps/tmp/downloads/"; // for droplet
    public static final String DOWNLOAD_PATH = "tmp/downloads/"; // for mac

    public static final String ZIP_PATH = "/tmp/zip_files";
    public static final String FILE_NAME = "report";
    public static final String SERVER_REPORT_URL = "/report/html";
    public static final String DOWNLOAD_FILE_PATH = DOWNLOAD_PATH + FILE_NAME;
    public static final String ZIPFILE = "/tmp/zip_files/cmed_report.zip";
    public static final String ZIPFILE_NAME = "cmed_report.zip";
    public static final String SRCDIR = "/tmp/downloads";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd-HH";

    public static WkHtmlToPdf initialiseWkHtmlToPdf() {
        //WkHtmlToPdf pdf = new WkHtmlToPdf(); // for mac
        //WkHtmlToPdf pdf = new WkHtmlToPdf("/usr/local/bin/wkhtmltopdf.sh"); // for centos server
        WkHtmlToPdf pdf = new WkHtmlToPdf("/usr/bin/wkhtmltopdf.sh"); // for ubuntu server
        return pdf;
    }

    public static WkHtmlToPdf initialiseWkHtmlToPdfLandscape() {
        WkHtmlToPdf pdf = new WkHtmlToPdf("-O landscape");
        return pdf;
    }

    public static String getServerAbsolutePath(String requestPath) throws MalformedURLException {
        String URL = getBaseURL() + requestPath;
        System.out.println("URL is "+ URL);
        return URL;
    }
    public static String getBaseURL() throws MalformedURLException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String baseUrl = "";
        if (request != null) {
            // handle proxy forward
            String scheme = request.getScheme();
            if (request.getHeader("x-forwarded-proto") != null) {
                scheme = request.getHeader("x-forwarded-proto");
            }

            Integer serverPort = request.getServerPort();
            if ((serverPort == 80) || (serverPort == 443)) {
                // No need to add the server port for standard HTTP and HTTPS ports, the scheme will help determine it.
                baseUrl = String.format("%s://%s%s", scheme, request.getServerName(), request.getContextPath());
            } else {
                baseUrl = String.format("%s://%s:%d%s", scheme, request.getServerName(), serverPort, request.getContextPath());
            }
        }
        return baseUrl;
    }




    public static String generateAcrReportOfEmployee(String SPECIFIC_PATH,
                                                   String govtId) {
        WkHtmlToPdf pdf = initialiseWkHtmlToPdf();

        try {
            String path = getServerAbsolutePath(SERVER_REPORT_URL
                    + SPECIFIC_PATH + "?govtId=" + govtId);
            pdf.addSources(Source.fromUrl(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String downloadPath = DOWNLOAD_FILE_PATH + "-AcrSummary" + ".pdf";

        pdf.addArguments(
                Argument.from(EnableJavascript));



        // Save the PDF
        File file = new File(downloadPath);

        System.out.println("Directory status: " + file.exists() + " " + file.isDirectory());
        System.out.println(downloadPath);

        try {
            pdf.save(Paths.get(downloadPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloadPath;
    }

}
