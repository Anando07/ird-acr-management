package com.sweetitech.ird.Mapper;

import com.sweetitech.ird.Domain.DTO.RequestDto.UserDTO;
import com.sweetitech.ird.Domain.Role;
import com.sweetitech.ird.Domain.User;
import com.sweetitech.ird.Service.RoleService;
import com.sweetitech.ird.common.Exception.EntityNotFoundException;
import com.sweetitech.ird.common.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sweetitech.ird.common.Util.PasswordUtil.EncType.BCRYPT_ENCODER;

@Service
public class UserMapper {

    @Autowired
    private RoleService roleService;

    /**
     * Maps DTO to Entity
     *
     * @param dto
     * @return entity
     */
    public User map(UserDTO dto) throws Exception {

        Role role = roleService.findRoleById(dto.getRoleId());
        if (role == null) {
            throw new EntityNotFoundException("Role does not exist");
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setDesignation(dto.getDesignation());
        entity.setUsername(dto.getUsername());
        entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), BCRYPT_ENCODER, null));
        entity.setPhone(dto.getPhone());
        entity.setRole(role);
        entity.setUserId(dto.getUserId());

        return entity;
    }

}