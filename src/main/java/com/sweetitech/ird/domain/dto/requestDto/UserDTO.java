package com.sweetitech.ird.domain.dto.requestDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)*/
public class UserDTO {

    private String name;

    private String designation;

    private String username;

    @Size(min = 2, max = 100, message = "Username character must be between 2 to 100!")
    private String userId;

    private String password;

    private String phone;

    @NotNull
    private Long roleId;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}