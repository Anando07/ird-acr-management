package com.avijit.ird.controller;

import com.avijit.ird.domain.ACR;
import com.avijit.ird.domain.Department;
import com.avijit.ird.domain.User;
import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.repository.AcrRepository;
import com.avijit.ird.repository.UserRepository;
import com.avijit.ird.service.DepartmentService;
import com.avijit.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 2:24 PM
 * @project ird
 */
@RestController
@RequestMapping("/secret")
public class SecretController {


    //these are secret controller only for our people
    @Autowired
    AcrRepository acrRepository;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping(value = "/assign/dept")
    public void assignDept() {
        Department department = departmentService.save("Default");
        List<ACR> acrs = acrRepository.findAll();
        for (ACR acr : acrs) {
            acr.setDepartment(department);
            acrRepository.save(acr);
        }
    }

    @GetMapping(value = "/all/users")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @PutMapping(value = "/update/user")
    public User updateUser(@RequestBody UserDTO dto) throws Exception {
        return userService.addUser(dto);
    }

}
