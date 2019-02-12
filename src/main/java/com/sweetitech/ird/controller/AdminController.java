package com.sweetitech.ird.controller;

import com.sweetitech.ird.common.Exception.EntityNotFoundException;
import com.sweetitech.ird.domain.dto.AcrDTO;
import com.sweetitech.ird.domain.dto.UserDTO;
import com.sweetitech.ird.mapper.UserMapper;
import com.sweetitech.ird.service.AcrFileService;
import com.sweetitech.ird.service.AcrService;
import com.sweetitech.ird.service.UserService;
import com.sweetitech.ird.serviceImpl.async.AsyncService;
import com.sweetitech.ird.validator.AcrFormValidator;
import com.sweetitech.ird.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.sweetitech.ird.report.ReportService.generateAcrReportOfEmployee;

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
    AcrFileService acrFileService;

    @Autowired
    UserFormValidator userFormValidator;


    @Autowired
    AcrFormValidator acrFormValidator;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AsyncService asyncService;


    @InitBinder("userRequestDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @InitBinder("acrDto")
    public void acrBinder(WebDataBinder binder) {
        binder.addValidators(acrFormValidator);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("userRequestDto", new UserDTO());
        return "admin/addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@Valid @ModelAttribute("userRequestDto") UserDTO userRequestDto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "admin/addUser";
        }
        userService.addUser(userRequestDto);
        return "redirect:/admin/userList";
    }

    @PostMapping(value = "/doUpdate")
    public String doUpdate(@Valid @ModelAttribute("userRequestDto") UserDTO userRequestDto, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("user", userRequestDto);
            model.addAttribute("existRoll", "existRoll");
            model.addAttribute("userlist", userService.userList());
            return "admin/userList";
        }
        userService.updateUser(userRequestDto);
        return "redirect:/admin/userList";
    }


    @GetMapping(value = "/deleteUser")
    public String deleteUSer(@RequestParam(name = "userId") String userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/userList";
    }


    @GetMapping(value = "/userList")
    public String getUserList(Model model) {
        List<UserDTO> list = userService.userList();
        model.addAttribute("user", new UserDTO());
        model.addAttribute("userlist", list);
        return "admin/userList";
    }


    @GetMapping(value = "/createAcr")
    public String createAcr(Model model) {
        model.addAttribute("acrDto", new AcrDTO());
        model.addAttribute("acrFiles", new ArrayList<>());
        return "admin/createAcr";
    }


    @PostMapping(value = "/createAcr")
    public String doCreateAcr(@ModelAttribute("acrDto") AcrDTO acrDto, BindingResult result) throws ParseException {
        System.out.println(acrDto.toString());
        if (result.hasErrors()) {
            return "admin/createAcr";
        }
        acrService.saveAcr(acrDto);
        return "redirect:/admin/acrList";
    }

    @GetMapping(value = "/acrList")
    public String findAllAcr(@RequestParam(value = "year", required = false) String year, Model model) {

        if (year == null) {
            model.addAttribute("list", acrService.acrOfCurrentYear());
        } else {
            model.addAttribute("list", acrService.getAcrByYear(year));
        }
        model.addAttribute("acr", new AcrDTO());
        return "admin/acrList";
    }


    @PostMapping(value = "/updateAcr")
    public String updateAcr(@ModelAttribute("acr") AcrDTO acr, BindingResult result, Model model) throws ParseException {
        System.out.println(acr.toString());
        acrService.updateAcr(acr);
        return "redirect:/admin/acrList";
    }


    @GetMapping(value = "/getacr")
    public String getAcr(@RequestParam("id") Long acrId, Model model) {
        AcrDTO dto = acrService.getSingleAcr(acrId);
        model.addAttribute("acrDto", dto);
        model.addAttribute("acrFiles", acrFileService.filesOfSingleAcr(acrId));
        return "admin/createAcr";
    }


    @GetMapping(value = "/view/acr")
    public ModelAndView getSingleAcr(@RequestParam("id") Long acrId) {
        ModelAndView mv = new ModelAndView("admin/acrList::acrDetails");
        AcrDTO dto = acrService.getSingleAcr(acrId);
        mv.addObject("acr",dto);
        mv.addObject("acrFiles", acrFileService.filesOfSingleAcr(acrId));
       return mv;
    }

    @GetMapping(value = "/deleteAcr")
    public String deleteAcr(@RequestParam(name = "id") Long id) {
        acrService.deleteAcr(id);
        return "redirect:/admin/acrList";
    }

    @GetMapping(value = "/getUser")
    public ModelAndView getUser(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("admin/userList::updateForm");
        mv.addObject("user", userMapper.responseMapper(userService.findById(id)));
        return mv;
    }

    @GetMapping(value = "/deleteFile")
    ModelAndView deleteFile(@RequestParam("fileId") Long fileId, @RequestParam("acrId") Long acrId) {
        System.out.println("getting inside controller " + fileId + " and " + acrId);

        asyncService.deleteFileFromStorage(fileId);

        acrFileService.deleteFile(fileId);

        ModelAndView mv = new ModelAndView("admin/createAcr::fileList");
        mv.addObject("acrFiles", acrFileService.filesOfSingleAcr(acrId));
        AcrDTO dto = acrService.getSingleAcr(acrId);
        mv.addObject("acrDto", dto);
        return mv;
    }



    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public ResponseEntity<Resource> getEmployeeList(@RequestParam String govtId) throws Exception {
        if (govtId == null) {
            throw new EntityNotFoundException("Null values found!");
        }

        System.out.println("govt id is "+ govtId);

        String downloadFilePath =
                generateAcrReportOfEmployee("/summary",
                        govtId);
        System.out.println("DL File Path: "+downloadFilePath);

        if (downloadFilePath == null)
            throw new NullPointerException("data missing");

        if (downloadFilePath == null)
            return ResponseEntity.badRequest()
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(null);

        File file = new File(downloadFilePath);

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=\"report-Summary" + ".pdf\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }


}

