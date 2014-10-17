package com.simbircite.demo.repository;

import com.simbircite.demo.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

    Iterable<User> findByUsername(String username);
}