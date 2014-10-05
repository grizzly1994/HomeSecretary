package com.simbircite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.repository.DebtRepo;

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

    public Iterable<Debt> get(Moment moment) {
        return repo.findAllByDateLessThanEqual(moment.getMoment());
    }
}