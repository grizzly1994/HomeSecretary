package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.repository.TransactionsRepository;
import com.simbircite.homesecretary.entity.Transaction;

public class TransactionService {
	@Autowired
	TransactionsRepository repository;
	
	public void add(Transaction data) {
		repository.save(data);
	}
	
	public void update(Transaction data) {
		repository.save(data);
	}
	
	public void delete(int id) {
		repository.delete(id);
	}
	
	public Iterable<Transaction> getAll() {
		return repository.findAll();
	}
	
	public Transaction getById(int id) {
		return repository.findOne(id);
	}
}
