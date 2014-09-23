package com.simbircite.demo.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simbircite.demo.model.Pay;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.service.EntityService;
import com.simbircite.demo.service.PayService;
import com.simbircite.demo.service.DebtService;
import com.simbircite.demo.util.DateUtil;
import com.simbircite.demo.util.spring.CustomDateTimeEditor;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    
	@Autowired
    private PayService pays;
	
	@Autowired
	private DebtService debts;
    
	private static final String ENTITY = "entity";
	private static final String PAYS = "pays";
    private static final String DEBTS = "debts";
	
    // View
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String actions(Model model) {
    	model.addAttribute("total", pays.total() + debts.rest());
    	model.addAttribute("rest", debts.rest());
        return "reports";
    }
    
    @RequestMapping(value = "{report}/add", method = RequestMethod.GET)
    public String showAdd(@PathVariable("report") String report, Model model) {
    	model.addAttribute(ENTITY, getEntity(report));
    	return report + "/add";
    }
    
    @RequestMapping(value = "{report}/update/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("report") String report, @PathVariable("id") int id, Model model) {
    	model.addAttribute(ENTITY, getService(report).get(id));
    	return report + "/update";
    }
    
    @RequestMapping(value = "{report}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object list(@PathVariable("report") String report) {
    	return getService(report).get();
    }
    
    // Actions
    
    @RequestMapping(value = "pays/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(ENTITY) Pay entity) {
    	pays.update(entity);
    	return "redirect:/reports";
    }
    
    @RequestMapping(value = "debts/update", method = RequestMethod.POST)
    public String update(@ModelAttribute(ENTITY) Debt entity) {
    	debts.update(entity);
    	return "redirect:/reports";
    }
    
    @RequestMapping(value = "{report}/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("report") String report, @PathVariable("id") int id, Model model) {
    	getService(report).delete(id);
    	return "redirect:/reports";
    }
    
    // Factories
    
    private Object getEntity(String report) {
    	if (PAYS.equals(report)) {
    		return new Pay();
    	}
    	if (DEBTS.equals(report)) {
    		return new Debt();
    	}
    	return null;
    }
    
    private EntityService getService(String report) {
    	if (PAYS.equals(report)) {
    		return pays;
    	}
    	if (DEBTS.equals(report)) {
    		return debts;
    	}
    	return null;
    }
    
    // Data formatter
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateUtil.getDateFormat());
        binder.registerCustomEditor(DateTime.class, new CustomDateTimeEditor(formatter));
    }
}
