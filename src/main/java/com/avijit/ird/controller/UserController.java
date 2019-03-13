package com.avijit.ird.controller;

import com.avijit.ird.domain.dto.AcrDTO;
import com.avijit.ird.service.AcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 2:12 PM
 * @project InternalResourcesDivision
 */

@Controller
public class UserController {

    @Autowired
    AcrService acrService;


    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value = "/user/govtId")
    public ModelAndView acrOfGovtId(@RequestParam("govtId") String govtId) {
        ModelAndView mv = new ModelAndView("home::acrList");
        List<AcrDTO> dtoList = acrService.getAcrOfGovtIdWithCurrentYear(govtId);
        mv.addObject("list", dtoList);

        return mv;
    }

    @GetMapping(value = "/user/allAcr")
    public ModelAndView allAcrOfGovtId(@RequestParam("govtId") String govtId) {
        ModelAndView mv = new ModelAndView("home::allAcr");
        List<AcrDTO> allAcr = acrService.getAllRequiredAcrByGovtId(govtId);
        mv.addObject("oldAcr", allAcr);
        return mv;
    }


    @GetMapping(value = "/user/notRequired/acr")
    public ModelAndView allNotRequiredAcrOfGovtId(@RequestParam("govtId") String govtId) {
        ModelAndView mv = new ModelAndView("home::notRequired");
        List<AcrDTO> allAcr = acrService.getAllNotRequiredAcrByGovtId(govtId);
        mv.addObject("notRequiredList", allAcr);
        return mv;
    }
}
