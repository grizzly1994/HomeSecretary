package com.simbircite.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import com.google.common.collect.*;
import com.simbircite.demo.model.*;
import com.simbircite.demo.service.*;


@Controller
@RequestMapping("/reports")
public class ReportsController {
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String actions() {
        return "reports";
    }
    
    @Autowired 
    private PayService service;
	
    @RequestMapping(value = "edit/{report}", method = RequestMethod.GET)
    public String add(@PathVariable("report") String report, Model model) {
    	Object attribute = new Pay();
    	model.addAttribute("model", attribute);
    	return "edit/" + report;
    }
    
    @RequestMapping(value = "edit/{report}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("model") Pay attribute) {
    	service.save(attribute);
        return "redirect:/reports";
    }
    
    @RequestMapping(value = "get/{report}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PayGrid list() {
    	PayGrid grid = new PayGrid();
    	grid.setUserData(Lists.newArrayList(service.get()));
    	return grid;
    }
}
