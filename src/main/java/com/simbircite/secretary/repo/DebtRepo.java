package com.simbircite.secretary.repo;

import org.joda.time.DateTime;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.User;

public interface DebtRepo extends PagingAndSortingRepository<Debt, Integer> {
    
    Iterable<Debt> findByUserAndDateLessThanEqualOrderByDateAsc(User user, DateTime date);
}
