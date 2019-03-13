package com.avijit.ird.controller;

import com.avijit.ird.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Avijit Barua
 * @created_on 3/13/19 at 5:13 PM
 * @project ird
 */

@Controller
@RequestMapping("/admin/logs")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("")
    public String logList(Model model) {
        model.addAttribute("logList", logService.getLogList());
        return "admin/logList";
    }
}
