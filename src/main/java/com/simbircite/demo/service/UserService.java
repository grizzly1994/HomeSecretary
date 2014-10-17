package com.simbircite.demo.service;

import com.simbircite.demo.model.User;
import com.simbircite.demo.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    public void add(User user) {
        repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repo.findByUsername(username).iterator().next();
    }
}