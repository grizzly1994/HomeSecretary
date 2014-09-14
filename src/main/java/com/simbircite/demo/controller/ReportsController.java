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
	
    @RequestMapping(value = "edit/{report}/{id}", method = RequestMethod.GET)
    public String edit(
    		@PathVariable("report") String report,
    		@PathVariable("id") int id,
    		Model model) {
    	Object attribute = new Pay();
    	if (id > -1) {
    		attribute = service.get(id);
    	}
    	model.addAttribute("model", attribute);
    	return "edit/" + report;
    }
    
    @RequestMapping(value = "remove/{report}/{id}", method = RequestMethod.GET)
    public String remove(
    		@PathVariable("report") String report,
    		@PathVariable("id") int id) {
    	service.delete(id);
    	return "redirect:/reports";
    }
    
    @RequestMapping(value = "edit/{report}", method = RequestMethod.POST)
    public String edit(@ModelAttribute("model") Pay attribute) {
    	service.save(attribute);
        return "redirect:/reports";
    }
    
    @RequestMapping(value = "get/{report}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public PayGrid list() {
    	PayGrid grid = new PayGrid();
    	grid.setUserData(Lists.newArrayList(service.get()));
    	return grid;
    }
}
