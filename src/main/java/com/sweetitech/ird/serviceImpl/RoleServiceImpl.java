package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.domain.Role;
import com.sweetitech.ird.repository.RoleRepository;
import com.sweetitech.ird.service.RoleService;
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