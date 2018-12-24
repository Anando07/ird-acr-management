package com.sweetitech.ird.ServiceImpl;

import com.sweetitech.ird.Domain.Role;
import com.sweetitech.ird.Repository.RoleRepository;
import com.sweetitech.ird.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id);
    }
}