package com.sweetitech.ird.Controller;

import com.sweetitech.ird.Domain.DTO.RequestDto.UserDTO;
import com.sweetitech.ird.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String addUser(@Valid @ModelAttribute("userDto") UserDTO user, BindingResult result, Model model) throws Exception {
        if(result.hasErrors())
        {
            return "addUser";
        }
        userService.addUser(user);
        return "";
    }
}
