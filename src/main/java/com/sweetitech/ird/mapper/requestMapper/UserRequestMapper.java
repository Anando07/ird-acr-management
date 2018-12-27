package com.sweetitech.ird.mapper.requestMapper;

import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.domain.Role;
import com.sweetitech.ird.domain.User;
import com.sweetitech.ird.service.RoleService;
import com.sweetitech.ird.common.Exception.EntityNotFoundException;
import com.sweetitech.ird.common.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sweetitech.ird.common.Util.PasswordUtil.EncType.BCRYPT_ENCODER;

@Service
public class UserRequestMapper {

    @Autowired
    private RoleService roleService;

    /**
     * Maps dto to Entity
     *
     * @param dto
     * @return entity
     */
    public User map(UserRequestDto dto) throws Exception {
        Role role = roleService.findRoleById(dto.getRoleId());
        if (role == null) {
            throw new EntityNotFoundException("Role does not exist");
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setDesignation(dto.getDesignation());
        entity.setUsername(dto.getUsername());
        if(dto.getPassword() !=null){
        entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), BCRYPT_ENCODER, null));}
        entity.setPhone(dto.getPhone());
        entity.setRole(role);
        entity.setUserId(dto.getUserId());
        return entity;
    }

}