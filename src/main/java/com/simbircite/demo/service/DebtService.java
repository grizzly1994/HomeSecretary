package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.model.Debt;
import com.simbircite.demo.repository.DebtRepository;

public class DebtService implements EntityService {
	
	@Autowired
	DebtRepository repository;
	
	public void add(Debt data) {
		repository.save(data);
	}
	
	public void update(Debt data) {
		repository.save(data);
	}
	
	@Override
	public Debt get(int id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
	
	public Iterable<Debt> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Object get() {
		return getAll();
	}
	
	public double rest() {
    	double value = 0;
    	for (Debt periodic : repository.findAll()) {
    		value += periodic.getBalance();
    	}
    	return value;
    }
}
