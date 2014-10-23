package com.simbircite.secretary.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.Pay;
import com.simbircite.secretary.model.User;
import com.simbircite.secretary.repo.PayRepo;

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
    public Iterable<Pay> getAll(User user, DateTime moment) {
        return repo.findByUserAndDateLessThanEqualOrderByDateAsc(user, moment);
    }
}