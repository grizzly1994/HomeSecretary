package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.repository.CategoryRepository;
import com.simbircite.homesecretary.entity.Category;

public class CategoryService {
	@Autowired
	CategoryRepository repository;
	
	public void add(Category data) {
		repository.save(data);
	}
	
	public void update(Category data) {
		repository.save(data);
	}
	
	public void delete(int id) {
		repository.delete(id);
	}
	
	public Iterable<Category> getAll() {
		return repository.findAll();
	}
	
	public Category getById(int id) {
		return repository.findOne(id);
	}
}
