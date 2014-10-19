package com.simbircite.demo.service;

import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.User;

public interface EntityListService {
    
    Object getAll(User user, Moment moment);
}
