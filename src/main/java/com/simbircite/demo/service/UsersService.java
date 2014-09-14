package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.repository.UsersRepository;
import com.simbircite.homesecretary.entity.Users;

public class UsersService {
	@Autowired
	UsersRepository repository;
	
	public void add(Users data) {
		repository.save(data);
	}
	
	public void update(Users data) {
		repository.save(data);
	}
	
	public void delete(int id) {
		repository.delete(id);
	}
	
	public Iterable<Users> getAll() {
		return repository.findAll();
	}
	
	public Users getById(int id) {
		return repository.findOne(id);
	}
}
