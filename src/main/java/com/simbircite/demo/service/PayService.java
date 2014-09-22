package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.model.Pay;
import com.simbircite.demo.repository.PayRepository;

public class PayService implements EntityService {
	
	@Autowired
	PayRepository repo;
	
	public void add(Pay data) {
		repo.save(data);
	}
	
	public void update(Pay data) {
		repo.save(data);
	}
	
	@Override
	public Pay get(int id) {
		return repo.findOne(id);
	}
	
	@Override
	public void delete(int id) {
		repo.delete(id);
	}
	
	@Override
	public Object get() {
		return repo.findAll();
	}
	
	public double total() {
		double total = 0;
    	for (Pay pay : repo.findAll()) {
    		total += pay.getBalance();
    	}
    	return total;
	}
}
