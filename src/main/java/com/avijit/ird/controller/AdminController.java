package com.avijit.ird.controller;

import com.avijit.ird.common.Util.DateUtil;
import com.avijit.ird.configuration.MySessionInfo;
import com.avijit.ird.domain.ACR;
import com.avijit.ird.domain.Department;
import com.avijit.ird.domain.dto.AcrDTO;
import com.avijit.ird.domain.dto.UserDTO;
import com.avijit.ird.report.ReportService;
import com.avijit.ird.common.Exception.EntityNotFoundException;
import com.avijit.ird.mapper.UserMapper;
import com.avijit.ird.repository.AcrRepository;
import com.avijit.ird.service.AcrFileService;
import com.avijit.ird.service.AcrService;
import com.avijit.ird.service.DepartmentService;
import com.avijit.ird.service.UserService;
import com.avijit.ird.serviceImpl.async.AsyncService;
import com.avijit.ird.validator.AcrFormValidator;
import com.avijit.ird.validator.UserFormValidator;
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
import java.util.Date;
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
    AcrFileService acrFileService;

    @Autowired
    UserFormValidator userFormValidator;

    @Autowired
    DepartmentService departmentService;


    @Autowired
    AcrFormValidator acrFormValidator;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AsyncService asyncService;

    @Autowired
    MySessionInfo sessionInfo;


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
        model.addAttribute("userId",sessionInfo.getCurrentUser().getId());
        return "admin/userList";
    }


    @GetMapping(value = "/createAcr")
    public String createAcr(Model model) {
        model.addAttribute("acrDto", new AcrDTO());
        model.addAttribute("acrFiles", new ArrayList<>());
        model.addAttribute("deptList", departmentService.getDepartments());
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

/*    @GetMapping(value = "/acrList")
    public String findAllAcr(Model model) {
        List <Department> deptList=departmentService.getDepartments();
        model.addAttribute("list", acrService.acrOfCurrentYear());
        model.addAttribute("deptList",deptList);
        model.addAttribute("acr", new AcrDTO());
        return "admin/acrList";

    }*/

    @GetMapping(value = "/acrList")
    public String findAcrByType(@RequestParam(name = "year", required = false) String year, @RequestParam(name = "deptId", required = false) Long deptId, Model model) {
        List<Department> deptList = departmentService.getDepartments();
        System.out.println("year "+year);
        String[] date = DateUtil.getReadableDate(new Date()).split(" ");

        if (deptList.size() < 1) {
            if (year == null) {
                model.addAttribute("list", acrService.acrOfCurrentYear());
            } else {
                model.addAttribute("list", acrService.getAcrByYear(year));
            }
        } else {
            if (year == null && deptId == null) {
                model.addAttribute("list", acrService.getAcrByYearAndDeptId(date[2],deptList.get(0).getId()));
            } else if (year == null && deptId != null) {
                model.addAttribute("list", acrService.getAcrByYearAndDeptId(date[2],deptId));
            } else {
                model.addAttribute("list", acrService.getAcrByYearAndDeptId(year, deptId));
            }
        }
        model.addAttribute("deptList", deptList);
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
        model.addAttribute("deptList", departmentService.getDepartments());
        model.addAttribute("acrFiles", acrFileService.filesOfSingleAcr(acrId));
        return "admin/createAcr";
    }


    @GetMapping(value = "/view/acr")
    public ModelAndView getSingleAcr(@RequestParam("id") Long acrId) {
        ModelAndView mv = new ModelAndView("admin/acrList::acrDetails");
        AcrDTO dto = acrService.getSingleAcr(acrId);
        mv.addObject("acr", dto);
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

    @PostMapping(value = "/reset/password")
    public String resetPassword(@RequestParam("user_Id") Long userId, @RequestParam("newPassword") String password) throws Exception {
        System.out.println("userId is " + userId + " and password is " + password);
        userService.resetPassword(userId, password);
        return "redirect:/admin/userList";
    }


    //did not use because BCC server didn't support WkHtmlToPdf
    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public ResponseEntity<Resource> getEmployeeList(@RequestParam String govtId) throws Exception {
        if (govtId == null) {
            throw new EntityNotFoundException("Null values found!");
        }

        System.out.println("govt id is " + govtId);

        String downloadFilePath =
                ReportService.generateAcrReportOfEmployee("/summary",
                        govtId);
        System.out.println("DL File Path: " + downloadFilePath);

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


    //this is secret method
    @Autowired
    AcrRepository acrRepository;
  @GetMapping(value = "/assign/dept")
  public void assignDept()
  {
      Department department = departmentService.save("Default");
      List<ACR> acrs = acrRepository.findAll();
      for(ACR acr : acrs)
      {
          acr.setDepartment(department);
          acrRepository.save(acr);
      }
  }




}

