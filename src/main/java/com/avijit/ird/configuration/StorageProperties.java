package com.avijit.ird.configuration;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 1:23 PM
 * @project InternalResourcesDivision
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    //private String location = "/opt/tomcat/webapps/abc"; //for ubuntu droplet
    private String location = ""; //for mac



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}