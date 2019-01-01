package com.sweetitech.ird.controller;

import com.sweetitech.ird.domain.dto.requestDto.AcrRequestDto;
import com.sweetitech.ird.domain.dto.requestDto.UserRequestDto;
import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.domain.dto.responseDto.UserResponseDto;
import com.sweetitech.ird.mapper.AcrResponseToRequestMapper;
import com.sweetitech.ird.mapper.responseMapper.UserResponseMapper;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import com.sweetitech.ird.validator.AcrFormValidator;
import com.sweetitech.ird.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    AcrResponseToRequestMapper mapper;

    @Autowired
    AcrFormValidator acrFormValidator;

    @Autowired
    UserResponseMapper userResponseMapper;

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
        return "redirect:/admin/userList";
    }

    @PostMapping(value = "/doUpdate")
    public String doUpdate(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto, BindingResult result,Model model) throws Exception {
        if(result.hasErrors())
        {
            model.addAttribute("user",userRequestDto);
            model.addAttribute("existRoll", "existRoll");
            model.addAttribute("userlist",userService.userList());
            return "admin/userList";
        }
        userService.updateUser(userRequestDto);

        return "redirect:/admin/userList";
    }

/*    @PostMapping(value = "/doUpdate")
    public ModelAndView doUpdate(@Valid @ModelAttribute("userRequestDto") UserRequestDto userRequestDto, BindingResult result, Model model) throws Exception {
    if(result.hasErrors())
        {
            model.addAttribute("hasError", "hasError");
            model.addAttribute("existRoll", "existRoll");
            model.addAttribute("user",userRequestDto);
            return new ModelAndView("admin/userList::updateForm");
        }
        userService.updateUser(userRequestDto);

        return new ModelAndView("redirect:/admin/userList");
    }*/

    @GetMapping(value = "/deleteUser")
    public String deleteUSer(@RequestParam(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/userList";
    }
    @GetMapping(value = "/userList")
    public String getUserList(Model model)
    {
        List<UserResponseDto> list = userService.userList();
        model.addAttribute("user", new UserRequestDto());
        model.addAttribute("userlist", list);

        return "admin/userList";
    }

    @GetMapping(value = "/createAcr")
    public String createAcr(Model model)
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
        return "redirect:/admin/acrlist";
    }

    @GetMapping(value = "/acrlist")
    public String findAllAcr(Model model)
    {
       model.addAttribute("list",acrService.acrOfCurrentYear());
       model.addAttribute("oldAcr",acrService.acrOfOldYear());
       model.addAttribute("acr",new AcrRequestDto());
       return "admin/acrList";
    }

    @PostMapping(value = "/updateAcr")
    public String updateAcr(@ModelAttribute("acr") AcrRequestDto acr, BindingResult result,Model model) throws ParseException {
        System.out.println(acr.toString());
        acrService.updateAcr(acr);
        return "redirect:/admin/acrlist";
    }

    @GetMapping(value = "/getacr")
    public String getAcr(@RequestParam("id") Long acrId,Model model)
    {
        AcrResponseDto dto= acrService.getSingleAcr(acrId);
        model.addAttribute("acrDto",mapper.map(dto));
        return "admin/createAcr";
    }

    @GetMapping(value = "/deleteAcr")
    public String deleteAcr(@RequestParam(name = "id") Long id) {
        acrService.deleteAcr(id);
        return "redirect:/admin/acrlist";
    }

    @GetMapping(value = "/getUser")
    public ModelAndView getUser(@RequestParam("id") Long id)
    {
        ModelAndView mv = new ModelAndView("admin/userList::updateForm");
        mv.addObject("user",userResponseMapper.map(userService.findById(id)));
        return mv;
    }
}
