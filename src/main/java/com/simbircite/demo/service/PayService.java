package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.Pay;
import com.simbircite.demo.repository.PayRepo;

public class PayService implements EntityService, EntityListService {

    @Autowired
    private PayRepo repo;

    public void add(Pay entity) {
        repo.save(entity);
    }

    public void update(Pay entity) {
        repo.save(entity);
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
    public Iterable<Pay> getAll(Moment moment) {
        return repo.findByDateLessThanEqualOrderByDateAsc(moment.getMoment());
    }
}