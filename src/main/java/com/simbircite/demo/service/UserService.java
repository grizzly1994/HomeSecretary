package com.simbircite.demo.service;

import com.simbircite.demo.model.User;
import com.simbircite.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("userService")
@Repository
public class UserService {

    @Autowired
    private UserRepository userRep;

    public void add(User user) {
        userRep.save(user);
    }

    public void update(User user) {
        userRep.save(user);
    }

    public void delete(User user) {
        userRep.delete(user);
    }

    public User getById(Long id) {
        return userRep.findOne(id);
    }

    public Iterable<User> getAll() {
        return userRep.findAll();
    }
    
    public Page<User> getAllByPage(Pageable pageable) {
        return userRep.findAll(pageable);
    }
}