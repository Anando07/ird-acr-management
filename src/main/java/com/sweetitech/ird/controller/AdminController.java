package com.sweetitech.ird.controller;

import com.sweetitech.ird.validator.AcrFormValidator;
import com.sweetitech.ird.validator.UserFormValidator;
import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    AcrFormValidator acrFormValidator;

    @InitBinder("userRequestDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @InitBinder("acrDto")
    public void acrBinder(WebDataBinder binder)
    {
        binder.addValidators(acrFormValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model)
    {
        model.addAttribute("userRequestDto",new UserRequestDto());
        return "admin/addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto, BindingResult result) throws Exception {
        if(result.hasErrors())
        {
            return "admin/addUser";
        }
        userService.addUser(userRequestDto);
        return "redirect:/admin/getUserList";
    }

    @PostMapping(value = "/doUpdate")
    public String doUpdate(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto, BindingResult result,Model model) throws Exception {
        if(result.hasErrors())
        {
            model.addAttribute("existRoll", "existRoll");
            model.addAttribute("hasError", "hasError");
            return "userList";
        }
        userService.updateUser(userRequestDto);

        return "redirect:/admin/getUserList";
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUSer(@RequestParam(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/getUserList";
    }
    @GetMapping(value = "/getUserList")
    public String getUserList(Model model)
    {
        List<UserResponseDto> list = userService.userList();
        model.addAttribute("userlist", list);
        return "admin/userList";
    }

    @GetMapping(value = "/createAcr")
    public String createAcr(@ModelAttribute("acrDto")AcrRequestDto acrDto, Model model)
    {
        model.addAttribute("acrDto", new AcrRequestDto());
        return "admin/createAcr";
    }

    @PostMapping(value = "/createAcr")
    public String doCreateAcr(@Valid @ModelAttribute("acrDto")AcrRequestDto acrDto, BindingResult result) throws ParseException {
        System.out.println(acrDto.toString());
        if(result.hasErrors())
        {
            return "admin/createAcr";
        }
        acrService.saveAcr(acrDto);
        return "redirect:/admin/getall";
    }

    @GetMapping(value = "/getall")
    public String findAllAcr(Model model)
    {
       model.addAttribute("list",acrService.acrOfCurrentYear());
       model.addAttribute("oldAcr",acrService.acrOfOldYear());
       return "admin/acrList";
    }
}
