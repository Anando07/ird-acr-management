/*
package com.sweetitech.ird.domain.authentication;

import javax.persistence.*;
import java.util.Arrays;

*/
/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:42 PM
 * @project InternalResourcesDivision
 *//*


@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken {

    @Id
    @GeneratedValue
    private String authenticationId;

    private String tokenId;

    @Lob
    private byte[] token;

    private String userName;

    private String clientId;

    @Lob
    private byte[] authentication;

    private String refreshToken;

    public OAuthAccessToken() {
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "OAuthAccessToken{" +
                "authenticationId='" + authenticationId + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", token=" + Arrays.toString(token) +
                ", userName='" + userName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", authentication=" + Arrays.toString(authentication) +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}*/
