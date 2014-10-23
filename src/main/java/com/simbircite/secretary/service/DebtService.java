package com.simbircite.secretary.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.User;
import com.simbircite.secretary.repo.DebtRepo;

public class DebtService implements EntityService {

    @Autowired
    private DebtRepo repo;

    public void add(Debt entity) {
        repo.save(entity);
    }
    
    public void update(Debt entity) {
        repo.save(entity);
    }

    @Override
    public Debt get(int id) {
        return repo.findOne(id);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }

    public Iterable<Debt> getAll(User user, DateTime moment) {
        return repo.findByUserAndDateLessThanEqualOrderByDateAsc(user, moment);
    }
}