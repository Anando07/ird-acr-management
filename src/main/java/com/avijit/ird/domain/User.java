package com.avijit.ird.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Avijit Barua
 * @created_on 12/9/18 at 5:02 PM
 * @project InternalResourcesDivision
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    public static final String SPLIT_CHAR = "::";

    @Column(columnDefinition = "TEXT")
    private String name;

    private String designation;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();



    private String username;

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 20, message = "UserId character must be between 2 to 20!")
    private String userId;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String phone;

    @ManyToOne
    private Role role;

    @JsonIgnore
    private boolean isDeleted = false;

    // Required for OAuth2
    @JsonIgnore
    private boolean userEnabled = true;
    @JsonIgnore
    private boolean accountNonExpired = true;
    @JsonIgnore
    private boolean accountNoLocked = true;
    @JsonIgnore
    private boolean credentialNonExpired = true;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public boolean isCredentialNonExpired() {
        return credentialNonExpired;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNoLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }


    @Override
    public boolean isEnabled() {
        return userEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        System.out.println("Authority for " + getUsername() + " -> " + authority.toString());
        authorities.add(authority);
        return authorities;
    }

    @JsonIgnore
    public String getPassword() {
        if (salt == null)
            return password;
        else
            return salt + SPLIT_CHAR + password;

    }

    public void setPassword(String password) {
        this.password = password;
        this.salt = null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", createdOn=" + createdOn +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", isDeleted=" + isDeleted +
                ", userEnabled=" + userEnabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNoLocked=" + accountNoLocked +
                ", credentialNonExpired=" + credentialNonExpired +
                '}';
    }
}