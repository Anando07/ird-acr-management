package com.sweetitech.ird.domain.dto.responseDto;

/**
 * @author Avijit Barua
 * @created_on 12/25/18 at 11:20 AM
 * @project ird
 */
public class UserResponseDto {

    private Long id;

    private String name;

    private String designation;

    private String username;

    private String userId;

    private String phone;

    private String roleType;

    private Long roleId;

    public UserResponseDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
