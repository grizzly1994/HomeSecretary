package com.simbircite.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.RestoreData;
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
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("auth", new User());
		return "user/register";
	}

	@RequestMapping(value = "restore", method = RequestMethod.GET)
	public String restore(Model model) {
		model.addAttribute("auth", new User());
		return "user/restore";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("auth") User auth, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
            return "user/register";
        }
		User user = new User();
		user.setUsername(auth.getUsername());
		user.setPassword(auth.getPassword());
		user.setCode(keyGenerationService.gen());
		user.setSalt(keyGenerationService.gen());
		encryptService.encrypt(user);
		try {
			userService.add(user);
		} catch (DataIntegrityViolationException error) {
			model.addAttribute("error", true);
			return "user/register";
		}
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("Home Secretary: email confirm");
		msg.setText("Click on this link to confirm your registration: " + request.getRequestURL() + "/" + auth.getCode());
		msg.setTo(auth.getEmail());
		mailSender.send(msg);
		return "redirect:/login";
	}

	@RequestMapping(value = "register/{code}", method = RequestMethod.GET)
	public String confirm(@PathVariable("code") String code) {
		User user = userService.loadUserByCode(code);
		user.setConfirmed(true);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "restore", method = RequestMethod.POST)
	public String restore(@Valid @ModelAttribute("auth") RestoreData auth, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/restore";
		}
		User user = userService.loadUserByUsername(auth.getUsername());
		if (user == null) {
			model.addAttribute("error", true);
			return "user/restore";
		}
		String password = keyGenerationService.gen();
		user.setPassword(password);
		encryptService.encrypt(user);
		userService.add(user);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("Home Secretary: password restoring");
		msg.setText("This is your temporary password: " + password);
		msg.setTo(auth.getUsername());
		mailSender.send(msg);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "login/error", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("error", true);
		return "user/login";
	}
}
