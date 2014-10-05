package com.simbircite.demo.repository;

import org.joda.time.DateTime;
import org.springframework.data.repository.CrudRepository;

import com.simbircite.demo.model.Debt;

public interface DebtRepo extends CrudRepository<Debt, Integer> {
    
    Iterable<Debt> findAllByDateLessThanEqual(DateTime date);
}
