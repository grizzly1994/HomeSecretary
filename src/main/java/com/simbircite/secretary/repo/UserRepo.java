package com.simbircite.secretary.repo;

import com.simbircite.secretary.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findByCode(String code);
}