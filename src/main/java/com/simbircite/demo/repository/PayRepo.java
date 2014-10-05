package com.simbircite.demo.repository;

import org.joda.time.DateTime;
import org.springframework.data.repository.*;

import com.simbircite.demo.model.*;

public interface PayRepo extends CrudRepository<Pay, Integer> {
    
    Iterable<Pay> findAllByDateLessThanEqual(DateTime date);
}
