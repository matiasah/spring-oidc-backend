package oidc.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserConsentController {
    
    @RequestMapping("consent")
    public ModelAndView consent() {
        return new ModelAndView("index.html");
    }

}