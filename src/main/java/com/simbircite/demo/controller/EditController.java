package com.simbircite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.simbircite.homesecretary.entity.*;

@Controller
@RequestMapping("/edit")
public class EditController {
    
    @RequestMapping(value = "actions", method = RequestMethod.GET)
    public ModelAndView show() {
    	Transaction transaction = new Transaction();
    	transaction.setComment("azaza");
    	return new ModelAndView("edit/actions", "transaction", transaction);
    }
    
    @RequestMapping(value = "actions", method = RequestMethod.POST)
    public String edit(@ModelAttribute("transaction") Transaction transaction) {
    	System.out.println(transaction.getComment());
        return "redirect:/reports";
    }
}
