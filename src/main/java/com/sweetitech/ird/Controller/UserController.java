package com.sweetitech.ird.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 2:12 PM
 * @project InternalResourcesDivision
 */

@Controller
public class UserController {
    @GetMapping("/")
    String login()
    {
        return "home";
    }
}
