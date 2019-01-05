package com.sweetitech.ird.controller;

import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.service.AcrService;
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
    public String home(Model model)
    {
        /*model.addAttribute("list",acrService.acrOfCurrentYear());
        model.addAttribute("oldAcr", acrService.acrOfOldYear());*/

        return "home";
    }
    @GetMapping(value = "/user/govtId")
    public ModelAndView acrOfGovtId(@RequestParam("govtId") String govtId)
    {
        ModelAndView mv = new ModelAndView("home::acrList");
        List<AcrResponseDto> dtoList = acrService.getAcrOfGovtIdWithCurrentYear(govtId);
        mv.addObject("list", dtoList);
        return mv;
    }

    @GetMapping(value = "/user/allAcr")
    public ModelAndView allAcrOfGovtId(@RequestParam("govtId") String govtId)
    {
        ModelAndView mv = new ModelAndView("home::allAcr");
        List<AcrResponseDto> allAcr = acrService.getAllAcrByGovtId(govtId);
        mv.addObject("oldAcr",allAcr);
        return mv;
    }

}
