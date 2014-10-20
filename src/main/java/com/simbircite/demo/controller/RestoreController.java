package com.simbircite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.User;
import com.simbircite.demo.service.GenService;
import com.simbircite.demo.service.MailService;
import com.simbircite.demo.service.UserService;

@Controller
@RequestMapping("/restore")
public class RestoreController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GenService genService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("entity", new User());
		return "user/restore";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String submit(@ModelAttribute("entity") User user) {
		user = userService.loadUserByUsername(user.getUsername());
		String pass = genService.gen();
		user.setPassword(pass);
		userService.updatePassword(user);
		String subject = "Home Secretary password recovery";
		String message = "It is your temporary password: " + pass;
		String to = user.getUsername();
		mailService.send(subject, message, to);
        return "user/login";
	}
}
