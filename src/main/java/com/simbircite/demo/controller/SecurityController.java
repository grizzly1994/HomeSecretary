package com.simbircite.demo.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simbircite.demo.form.Message;

@Controller
@RequestMapping("/security")
public class SecurityController {

    private static final Logger logger = Logger.getLogger(SecurityController.class);
    
    @Autowired
    private MessageSource messageSource; 

    @RequestMapping(value = "/loginfail")
    public String loginFail(Model uiModel, Locale locale) {
        logger.info("Login failed detected");
        uiModel.addAttribute("message", new Message("error",
                messageSource.getMessage("login.fail.message", new Object[] {}, locale)));
        return "users/list";
    }
}
