package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.simbircite.demo.repository.*;
import com.simbircite.demo.model.*;

@Service("PayService")
@Repository
public class PayService {
	
	@Autowired
	PayRepository repo;
	
	public void save(Pay data) {
		repo.save(data);
	}
	
	public void delete(int id) {
		repo.delete(id);
	}
	
	public Iterable<Pay> get() {
		return repo.findAll();
	}
	
	public Pay get(int id) {
		return repo.findOne(id);
	}
}
