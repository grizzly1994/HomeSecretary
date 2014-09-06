package com.simbircite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/edit")
public class EditController {

    private static final String PATH = "edit/";
    
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String show(@PathVariable("page") String page) {
        return PATH + page;
    }
}
