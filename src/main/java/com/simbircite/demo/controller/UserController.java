package com.simbircite.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.User;
import com.simbircite.demo.service.EncryptService;
import com.simbircite.demo.service.KeyGenerationService;
import com.simbircite.demo.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MailSender mailSender;

	@Autowired
	KeyGenerationService keyGenerationService;

	@Autowired
	EncryptService encryptService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "user/login";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("entity", new User());
		return "user/register";
	}

	@RequestMapping(value = "restore", method = RequestMethod.GET)
	public String restore(Model model) {
		model.addAttribute("entity", new User());
		return "user/restore";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("entity") User user, HttpServletRequest request) {
		user.setCode(keyGenerationService.gen());
		user.setSalt(keyGenerationService.gen());
		encryptService.encrypt(user);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("Home Secretary: email confirm");
		msg.setText("Click on this link to confirm your registration: " + request.getRequestURL() + "/" + user.getCode());
		msg.setTo(user.getEmail());
		mailSender.send(msg);
		userService.add(user);
		return "redirect:/login";
	}

	@RequestMapping(value = "register/{code}", method = RequestMethod.GET)
	public String confirm(@PathVariable("code") String code) {
		User user = userService.loadUserByCode(code);
		user.setConfirmed(true);
		return "redirect:/login";
	}

	@RequestMapping(value = "restore", method = RequestMethod.POST)
	public String restore(User user) {
		user = userService.loadUserByUsername(user.getUsername());
		String password = keyGenerationService.gen();
		user.setPassword(password);
		encryptService.encrypt(user);
		userService.add(user);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("Home Secretary: password restoring");
		msg.setText("This is your temporary password: " + password);
		msg.setTo(user.getUsername());
		mailSender.send(msg);
		return "redirect:/login";
	}
}
