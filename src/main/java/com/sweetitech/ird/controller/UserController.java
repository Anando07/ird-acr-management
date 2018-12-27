package com.sweetitech.ird.controller;

import com.sweetitech.ird.domain.dto.responseDto.AcrResponseDto;
import com.sweetitech.ird.service.AcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 2:12 PM
 * @project InternalResourcesDivision
 */

@Controller
public class UserController {

    @Autowired
    AcrService acrService;

    @GetMapping("/home")
    String login(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model)
    {
        int totalPages = acrService.acrOfCurrentYear(page).getTotalPages();
        if(totalPages > 0) {

            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            //modelAndView.addObject("pageNumbers", pageNumbers);
            model.addAttribute("pageNumbers",pageNumbers);
        }

        List<AcrResponseDto>list = acrService.acrOfCurrentYear(page).getContent();
        model.addAttribute("list",list);
        return "home";
    }

    @GetMapping("/test")
    ModelAndView dashboard(@RequestParam(value = "page", defaultValue = "0") Integer page)
    {
        ModelAndView modelAndView = new ModelAndView("home::article-list");
        int totalPages = acrService.acrOfCurrentYear(page).getTotalPages();
        if(totalPages > 0) {

            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        List<AcrResponseDto>list = acrService.acrOfCurrentYear(page).getContent();
        modelAndView.addObject("list",list);
        return modelAndView;
    }
}
