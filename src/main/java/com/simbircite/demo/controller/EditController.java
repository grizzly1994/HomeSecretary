package com.simbircite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/edit")
public class EditController {
    
    @RequestMapping(value = "{report}", method = RequestMethod.GET)
    public String show(@PathVariable("report") String report) {
        return "edit/" +  report;
    }
    
    @RequestMapping(value = "{report}", method = RequestMethod.POST)
    public String edit(@PathVariable("report") String report) {
        return "redirect:/reports";
    }
}
