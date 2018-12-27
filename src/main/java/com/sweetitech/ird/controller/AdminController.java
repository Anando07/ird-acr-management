package com.sweetitech.ird.controller;

import com.sweetitech.ird.common.Util.UserFormValidator;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.pageable.UserResponsePage;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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

    @Autowired
    AcrService acrService;

    @Autowired
    UserFormValidator userFormValidator;

    @InitBinder("userRequestDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model)
    {
        model.addAttribute("userRequestDto",new UserRequestDto());
        return "admin/addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto, BindingResult result, Model model) throws Exception {
        if(result.hasErrors())
        {
            return "admin/addUser";
        }
        userService.addUser(userRequestDto);
        return "redirect:/admin/getUserList";
    }
    @GetMapping(value = "/getUserList")
    public String getUserList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model)
    {
        UserResponsePage userpage = userService.getAllUsers(page);
        model.addAttribute("userpage", userpage);
        return "admin/userList";
    }


    @GetMapping(value = "/deleteUser")
    public String deleteUSer(@RequestParam(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/getUserList";
    }

    @GetMapping(value = "/createAcr")
    public String createAcr(@ModelAttribute("acrDto")AcrRequestDto acrDto, Model model)
    {
        model.addAttribute("acrDto", new AcrRequestDto());
        return "admin/createAcr";
    }

    @PostMapping(value = "/createAcr")
    public String doCreateAcr(@ModelAttribute("acrDto")AcrRequestDto acrDto) throws ParseException {
        System.out.println(acrDto.toString());
        acrService.saveAcr(acrDto);
        return "admin/createAcr";
    }

    @GetMapping(value = "/acrlist")
    public String getAcrList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model)
    {
        List<AcrResponseDto> list = acrService.acrOfCurrentYear(page).getContent();
        model.addAttribute("list",list);
        return "admin/acrList";
    }

    @PostMapping(value = "/doUpdate")
    public String doUpdate(@Valid @ModelAttribute("userDto") UserRequestDto userDto, BindingResult result,Model model) throws Exception {
        System.out.println(userDto.toString());
        if(result.hasErrors())
        {
            model.addAttribute("existRoll", "existRoll");
            model.addAttribute("hasError", "hasError");
          return "userList";
        }
        userService.updateUser(userDto);

        return "redirect:/admin/getUserList";
    }
}
