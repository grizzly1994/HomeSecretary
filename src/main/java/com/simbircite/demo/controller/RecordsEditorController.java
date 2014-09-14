package com.simbircite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simbircite.demo.model.User;
import com.simbircite.demo.service.UserService;
import com.simbircite.demo.validator.UserValidator;
import com.sun.mail.smtp.SMTPTransport;

import java.util.*;
import java.security.Security;
import java.text.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



@Controller
@RequestMapping("/Avtor")
public class RecordsEditorController {

    private static final String CONTROLLER_PATH = "Avtor/";
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return CONTROLLER_PATH + "register";
    }
    
   
    
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String enter() {
        return CONTROLLER_PATH + "enter";
    }
   
   
    
    @RequestMapping(value = "/remember", method = RequestMethod.GET)
    public String remember() {
        return CONTROLLER_PATH + "remember";
    }
    
    @RequestMapping(value = "/remembertwo", method = RequestMethod.GET)
    public String remembertwo() {
        return CONTROLLER_PATH + "remembertwo";
    }
    
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirm() {
        return CONTROLLER_PATH + "confirm";
    }
    
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send() {
    	
    	try {
         Properties mailProps=new Properties();
         mailProps.put("mail.smtp.host","smtp.gmail.com");
         mailProps.put("mail.smtp.auth", "true");
         mailProps.put("mail.smtp.starttls.enable", "true");
         mailProps.put("mail.smtp.port", "587");
         System.out.println( "Set prop!");
         
         javax.mail.Authenticator authenticator = new org.apache.commons.mail.DefaultAuthenticator("jul.klimova2013@gmail.com","76532022");

         Session mailSession = Session.getInstance(mailProps, authenticator);
         
         
         mailSession.setDebug(true);
         System.out.println( "Set session!");
         
         MimeMessage message=new MimeMessage(mailSession);
         message.setFrom(new InternetAddress("jul.klimova2013@gmail.com"));
         String[] emails={"d.matushin@gmail.com", "jul.klimova2013@gmail.com"}; //адреса получателей
         InternetAddress dests[]=new InternetAddress[emails.length];
         for(int i=0; i<emails.length; i++){
         	dests[i]=new InternetAddress(emails[i].trim().toLowerCase());
         }
         message.setRecipients(Message.RecipientType.TO, dests);
         message.setSubject("тема письма","KOI8-R");

         message.setContent("Change password", "text/html");
         message.setSentDate(new java.util.Date());
         System.out.println( "Set message!");
         Transport.send(message);
         System.out.println( "Send!");
         
         System.out.println( "Done!");
     	
     		return CONTROLLER_PATH + "enter";
     	
    	} catch (Exception e) {
    		System.out.println("Error!");
    		return CONTROLLER_PATH + "remember";
    	}

    }
   

}
    


