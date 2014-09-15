package com.simbircite.demo.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.*;
import com.simbircite.demo.model.*;
import com.simbircite.demo.service.*;
import com.simbircite.demo.util.DateUtil;
import com.simbircite.demo.util.spring.CustomDateTimeEditor;
import com.simbircite.homesecretary.entity.PeriodicTransaction;
import com.simbircite.homesecretary.entity.PeriodicTransactionGrid;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    
	@Autowired
    private PayService payService;
	
	@Autowired
	private PeriodicTransactionService periodicService;
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String actions(Model model) {
    	model.addAttribute("total", total() + rest());
    	model.addAttribute("rest", rest());
        return "reports";
    }
    
    double total() {
    	double total = 0;
    	for (Pay pay : payService.get()) {
    		total += pay.getBalance();
    	}
    	return total;
    }
	
    double rest() {
    	double rest = 0;
    	for (PeriodicTransaction periodic : periodicService.getAll()) {
    		rest += periodic.getBalance();
    	}
    	return rest;
    }
    
    // Actions
    
    @RequestMapping(value = "edit/actions/{id}", method = RequestMethod.GET)
    public String editActions(@PathVariable("id") int id, Model model) {
    	Object attribute = new Pay();
    	if (id > -1) {
    		attribute = payService.get(id);
    	}
    	model.addAttribute("model", attribute);
    	return "edit/actions";
    }
    
    @RequestMapping(value = "edit/actions", method = RequestMethod.POST)
    public String editAction(@ModelAttribute("model") Pay attribute) {
    	payService.save(attribute);
        return "redirect:/reports";
    }
    
    @RequestMapping(value = "remove/actions/{id}", method = RequestMethod.GET)
    public String removeAction(@PathVariable("id") int id) {
    	payService.delete(id);
    	return "redirect:/reports";
    }
    
    @RequestMapping(value = "get/actions", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public PayGrid listActions() {
    	PayGrid grid = new PayGrid();
    	grid.setUserData(Lists.newArrayList(payService.get()));
    	return grid;
    }
    
    // Periodic
    
    @RequestMapping(value = "edit/periodic/{id}", method = RequestMethod.GET)
    public String editPeriodic(@PathVariable("id") int id, Model model) {
    	Object attribute = new PeriodicTransaction();
    	if (id > -1) {
    		attribute = periodicService.getById(id);
    	}
    	model.addAttribute("model", attribute);
    	return "edit/periodic";
    }
    
    @RequestMapping(value = "remove/periodic/{id}", method = RequestMethod.GET)
    public String removePeriodic(@PathVariable("id") int id) {
    	periodicService.delete(id);
    	return "redirect:/reports";
    }
    
    @RequestMapping(value = "edit/periodic", method = RequestMethod.POST)
    public String editPeriodic(@ModelAttribute("model") PeriodicTransaction attribute) {
    	periodicService.update(attribute);
        return "redirect:/reports";
    }
    
    @RequestMapping(value = "get/periodic", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PeriodicTransactionGrid listPeriodic() {
    	PeriodicTransactionGrid grid = new PeriodicTransactionGrid();
    	grid.setUserData(Lists.newArrayList(periodicService.getAll()));
    	return grid;
    }
    
    // Date formatter
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateUtil.getDateFormat());
        binder.registerCustomEditor(
                DateTime.class, new CustomDateTimeEditor(formatter));
    }
}
