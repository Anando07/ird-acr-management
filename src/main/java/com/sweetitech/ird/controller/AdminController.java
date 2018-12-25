package com.sweetitech.ird.controller;

import com.sweetitech.ird.domain.dto.requestDto.UserDTO;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.pageable.UserResponsePage;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 6:30 PM
 * @project InternalResourcesDivision
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/addUser")
    public String addUser(Model model)
    {
        model.addAttribute(new UserDTO());
        return "addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("userDto") UserDTO userDto, BindingResult result, Model model) throws Exception {
        if(result.hasErrors())
        {
            return "addUser";
        }
        userService.addUser(userDto);
        return "redirect:/admin/getUserList";
    }
    @GetMapping(value = "/getUserList")
    public String getUserList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model)
    {
        UserResponsePage userpage = userService.getAllUsers(page);
        model.addAttribute("userpage", userpage);
        return "userList";
    }
}