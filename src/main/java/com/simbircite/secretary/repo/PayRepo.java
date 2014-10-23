package com.simbircite.secretary.repo;

import org.joda.time.DateTime;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.secretary.model.Pay;
import com.simbircite.secretary.model.User;

public interface PayRepo extends PagingAndSortingRepository<Pay, Integer> {
    
    Iterable<Pay> findByUserAndDateLessThanEqualOrderByDateAsc(User user, DateTime date);
}
