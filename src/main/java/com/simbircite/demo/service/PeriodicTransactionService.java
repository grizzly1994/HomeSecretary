package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.repository.PeriodicTransactionRepository;
import com.simbircite.homesecretary.entity.PeriodicTransaction;

public class PeriodicTransactionService {
	@Autowired
	PeriodicTransactionRepository repository;
	
	public void add(PeriodicTransaction data) {
		repository.save(data);
	}
	
	public void update(PeriodicTransaction data) {
		repository.save(data);
	}
	
	public void delete(int id) {
		repository.delete(id);
	}
	
	public Iterable<PeriodicTransaction> getAll() {
		return repository.findAll();
	}
	
	public PeriodicTransaction getById(int id) {
		return repository.findOne(id);
	}
}
