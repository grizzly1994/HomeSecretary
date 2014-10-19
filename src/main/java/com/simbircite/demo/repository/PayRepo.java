package com.simbircite.demo.repository;

import org.joda.time.DateTime;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.demo.model.Pay;
import com.simbircite.demo.model.User;

public interface PayRepo extends PagingAndSortingRepository<Pay, Integer> {
    
    Iterable<Pay> findByUserAndDateLessThanEqualOrderByDateAsc(User user, DateTime date);
}
