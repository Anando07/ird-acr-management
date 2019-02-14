package com.avijit.ird.serviceImpl;

import com.avijit.ird.common.Util.PasswordUtil;
import com.avijit.ird.domain.Role;
import com.avijit.ird.domain.User;
import com.avijit.ird.repository.RoleRepository;
import com.avijit.ird.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository,UserRepository userRepository ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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


        if (roleRepository.findById(2) == null)
            roleRepository.save(new Role(2,"ROLE_OPERATOR"));
        else if (!roleRepository.findById(2).getName().equals("ROLE_OPERATOR")) {
            Role oldAdminDate = roleRepository.findById(2);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_OPERATOR");
            roleRepository.save(role);
        }

        if(userRepository.findByUserId("admin") == null)
        {
            User user = new User();
            user.setUsername("admin");
            user.setUserId("admin");
            user.setPassword(PasswordUtil.encryptPassword("123456", PasswordUtil.EncType.BCRYPT_ENCODER, null));

            Role role = roleRepository.findById(1);
            user.setRole(role);
            userRepository.save(user);
        }

    }
}