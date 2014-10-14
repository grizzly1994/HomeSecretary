package com.simbircite.demo.repository;

import org.joda.time.DateTime;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.demo.model.Debt;

public interface DebtRepo extends PagingAndSortingRepository<Debt, Integer> {
    
    Iterable<Debt> findByDateLessThanEqualOrderByDateAsc(DateTime date);
}
