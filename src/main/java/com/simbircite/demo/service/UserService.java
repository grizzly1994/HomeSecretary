package com.simbircite.demo.service;

import com.simbircite.demo.model.User;
import com.simbircite.demo.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private EncryptService encryptService;

	@Autowired
	private GenService genService;

	@Autowired
	private MailService mailService;

	public void add(User user) {
		user.setCode(genService.gen());
		user.setSalt(genService.gen());
		updatePassword(user);
		String subject = "Home Secretary: email confirm";
		String text = "Click on this link to confirm the account: http://localhost:8080/SpringJpaDemo/register/" + user.getCode();
		String to = user.getUsername();
		mailService.send(subject, text, to);
	}

	public void updatePassword(User user) {
		String pass = user.getPassword();
		String salt = user.getSalt();
		pass = encryptService.encrypt(pass, salt);
		user.setPassword(pass);
		user.setSalt(salt);
		repo.save(user);
	}

	public void updateConfirmed(User user) {
		user.setConfirmed(true);
		repo.save(user);
	}
	
	@Override
    public User loadUserByUsername(String username) {
    	User user = repo.findByUsername(username).iterator().next();
    	return user.isConfirmed() ? user : null;
    }
	
	public User loadUserByCode(String code) {
		User user = repo.findByCode(code).iterator().next();
		return user;
	}
}