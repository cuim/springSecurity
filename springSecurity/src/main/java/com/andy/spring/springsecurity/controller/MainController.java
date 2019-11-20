package com.andy.spring.springsecurity.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/login-error")
    public ModelAndView loginError(Model model) {
        model.addAttribute( "loginError"  , true);
        return new ModelAndView("login");
    }

    @GetMapping("/401")
    public ModelAndView accessDenied() {
        return new ModelAndView("401");
    }

    @GetMapping("/user/common")
    public ModelAndView common() {
        return new ModelAndView("user/common");
    }

    @GetMapping("/user/admin")
    public ModelAndView admin() {
        return new ModelAndView("user/admin");
    }


}

