package oidc.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login controller
 *
 * @author Matías Hermosilla
 * @since 29-01-2022
 */
@Controller
public class LoginController {
    
    // Login form
    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("index.html");
    }

}