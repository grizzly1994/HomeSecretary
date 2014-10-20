package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailService {

	@Autowired
	MailSender mailSender;
	
	public void send(String subject, String message, String to) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject(subject);
		msg.setText(message);
        msg.setTo(to);
        mailSender.send(msg);
	}
}
