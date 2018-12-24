package com.sweetitech.ird.Domain.Authentication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:44 PM
 * @project InternalResourcesDivision
 */

@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken {

    @Id
    private String tokenId;

    @Lob
    private byte[] token;

    @Lob
    private byte[] authentication;

    public OauthRefreshToken() {
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

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "OauthRefreshToken{" +
                ", tokenId='" + tokenId + '\'' +
                ", token=" + Arrays.toString(token) +
                ", authentication=" + Arrays.toString(authentication) +
                '}';
    }
}