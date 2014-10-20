package com.simbircite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.User;
import com.simbircite.demo.service.GenService;
import com.simbircite.demo.service.UserService;

@Controller
@RequestMapping("/restore")
public class RestoreController {
	
	@Autowired
	MailSender mailSender;
	
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
		SimpleMailMessage msg = new SimpleMailMessage();
		user = userService.loadUserByUsername(user.getUsername());
		String pass = genService.gen();
		user.setPassword(pass);
		userService.update(user);
		msg.setSubject("Home Secretary password recovery");
		msg.setText("It is your temporary password: " + pass);
        msg.setTo(user.getUsername());
        mailSender.send(msg);
        return "user/login";
	}
}
