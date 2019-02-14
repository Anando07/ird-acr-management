package com.avijit.ird.mapper;

import com.avijit.ird.common.Util.PasswordUtil;
import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.common.Exception.EntityNotFoundException;
import com.avijit.ird.domain.Role;
import com.avijit.ird.domain.User;
import com.avijit.ird.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Avijit Barua
 * @created_on 2/10/19 at 3:18 PM
 * @project ird
 */
@Service
public class UserMapper {
    @Autowired
    private RoleService roleService;


    public User requestMapper(UserDTO dto) throws Exception {
        Role role = roleService.findRoleById(dto.getRoleId());
        if (role == null) {
            throw new EntityNotFoundException("Role does not exist");
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setDesignation(dto.getDesignation());
        entity.setUsername(dto.getUsername());
        if (dto.getPassword() != null) {
            entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), PasswordUtil.EncType.BCRYPT_ENCODER, null));
        }
        entity.setPhone(dto.getPhone());
        entity.setRole(role);
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUserId());
        return entity;
    }

    public UserDTO responseMapper(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDesignation(user.getDesignation());
        dto.setUserId(user.getUserId());
        dto.setPhone(user.getPhone());

        Role role = roleService.findRoleById(user.getRole().getId());
        dto.setRoleId(role.getId());

        if (role.getName().equalsIgnoreCase("ROLE_ADMIN")) {
            dto.setRoleType("ADMIN");
        } else {
            dto.setRoleType("OPERATOR");
        }

        return dto;
    }
}
