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
    
    public void add(User user) {
    	user.setConfirm(genService.gen());
    	user.setSalt(genService.gen());
    	update(user);
    }
    
    public void update(User user) {
    	String pass = user.getPassword();
    	String salt = user.getSalt();
    	pass = encryptService.encrypt(pass, salt);
    	user.setPassword(pass);
    	user.setSalt(salt);
        repo.save(user);
    }
    
    @Override
    public User loadUserByUsername(String username) {
    	return repo.findByUsername(username).iterator().next();
    }
}