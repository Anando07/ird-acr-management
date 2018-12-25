package com.sweetitech.ird.serviceImpl;

import com.sweetitech.ird.domain.Role;
import com.sweetitech.ird.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load User Roles
        if (roleRepository.findById(1) == null)
            roleRepository.save(new Role(1,"ROLE_ADMIN"));
        else if (!roleRepository.findById(1).getName().equals("ROLE_ADMIN")) {
            Role oldAdminDate = roleRepository.findById(1);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

//        if (roleRepository.findById(2) == null)
//            roleRepository.save(new Role(2,"ROLE_EMPLOYEE"));
//        else if (!roleRepository.findById(2).getName().equals("ROLE_EMPLOYEE")) {
//            Role oldAdminDate = roleRepository.findById(2);
//            Role role = new Role();
//            role.setId(oldAdminDate.getId());
//            role.setName("ROLE_EMPLOYEE");
//            roleRepository.save(role);
//        }

    }
}