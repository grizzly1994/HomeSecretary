package com.simbircite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.User;
import com.simbircite.demo.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("entity", new User());
        return "user/register";
    }
    
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/login";
    }
    
    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public String confirm(@PathVariable String code) {
    	User user = userService.loadUserByCode(code);
    	userService.updateConfirmed(user);
    	return "redirect:/login";
    }
}