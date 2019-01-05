package com.sweetitech.ird.mapper.responseMapper;

import com.sweetitech.ird.domain.Role;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Avijit Barua
 * @created_on 12/25/18 at 11:42 AM
 * @project ird
 */
@Service
public class UserResponseMapper {

    @Autowired
    RoleService roleService;

    public UserResponseDto map(User user)
    {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDesignation(user.getDesignation());
        dto.setUserId(user.getUserId());
        dto.setPhone(user.getPhone());

        Role role  = roleService.findRoleById(user.getRole().getId());
        dto.setRoleId(role.getId());

        if(role.getName().equalsIgnoreCase("ROLE_ADMIN"))
        {
            dto.setRoleType("ADMIN");
        }
        else
        {
            dto.setRoleType("OPERATOR");
        }

        return dto;
    }
}
