package com.simbircite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Avtor")
public class RecordsEditorController {

    private static final String CONTROLLER_PATH = "Avtor/";
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return CONTROLLER_PATH + "register";
    }
    
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String enter() {
        return CONTROLLER_PATH + "enter";
    }
    
    @RequestMapping(value = "/remember", method = RequestMethod.GET)
    public String remember() {
        return CONTROLLER_PATH + "remember";
    }
    
    @RequestMapping(value = "/remembertwo", method = RequestMethod.GET)
    public String remembertwo() {
        return CONTROLLER_PATH + "remembertwo";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirm() {
        return CONTROLLER_PATH + "confirm";
    }
    
}

