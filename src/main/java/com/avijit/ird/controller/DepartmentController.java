package com.avijit.ird.controller;

import com.avijit.ird.domain.Department;
import com.avijit.ird.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avijit Barua
 * @created_on 3/5/19 at 2:30 PM
 * @project ird
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/all")
    String getDepartmentList(Model model) {
        model.addAttribute("list", departmentService.getDepartments());
        model.addAttribute("department", new Department());
        return "admin/departmentList";
    }

    @GetMapping(value = "/remove")
    String removeDepartment(@RequestParam("deptId") Long deptId) {
        departmentService.delete(deptId);
        return "redirect:/admin/department/all";
    }

    @GetMapping(value = "/read")
    ModelAndView getDepartment(@RequestParam Long deptId) {
        ModelAndView mv = new ModelAndView("admin/departmentList::updateDept");
        mv.addObject("department", departmentService.readDepartment(deptId));
        return mv;
    }

    @PostMapping(value = "/update")
    String updateDepartment(Department department) {
        departmentService.update(department);
        return "redirect:/admin/department/all";
    }

    @PostMapping(value = "/save")
    String saveDepartment(@RequestParam("deptName") String deptName)
    {
        departmentService.save(deptName);
        return "redirect:/admin/department/all";
    }

}
