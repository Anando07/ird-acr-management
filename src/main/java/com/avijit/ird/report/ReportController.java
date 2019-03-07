package com.avijit.ird.report;

import com.avijit.ird.common.Exception.NotFoundException;
import com.avijit.ird.domain.dto.AcrDTO;
import com.avijit.ird.service.AcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 2/12/19 at 2:14 PM
 * @project ird
 */
@Controller
@RequestMapping("/report/html")
public class ReportController{

    @Autowired
    AcrService acrService;

    @GetMapping(value = "/summary")
    public String generateAcrSummaryByGovtId(@RequestParam String govtId, Model model) throws NotFoundException {
        model.addAttribute("summary",acrService.getAllAcrByGovtId(govtId));
        return "report/acrSummary";
    }

}
