package com.sweetitech.ird.SecurityConfig.oauth;

import com.sweetitech.ird.SecurityConfig.component.ApiError;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

/**
 * @author Avijit Barua
 * @created_on 1/1/19 at 3:58 PM
 * @project ird
 */
@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SettingsRepository {

    private String accessToken;

    private String refreshToken;

    private String name;
    private String password;
    private String userId;


    private String roleid;
    private String rolename;

    private String isFirstLoggedin;


    public String getIsFirstLoggedin() {
        return isFirstLoggedin;
    }

    public void setIsFirstLoggedin(String isFirstLoggedin) {
        this.isFirstLoggedin = isFirstLoggedin;
    }



    private ApiError apierror;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public ApiError getApierror() {
        return apierror;
    }

    public void setApierror(ApiError apierror) {
        this.apierror = apierror;
    }


    @Override
    public String toString() {
        return "SettingsRepository{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", roleid='" + roleid + '\'' +
                ", rolename='" + rolename + '\'' +
                ", isFirstLoggedin='" + isFirstLoggedin + '\'' +
                ", apierror=" + apierror +
                '}';
    }
}
