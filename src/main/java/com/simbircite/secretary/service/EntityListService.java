package com.simbircite.secretary.service;

import org.joda.time.DateTime;

import com.simbircite.secretary.model.User;

public interface EntityListService {
    
    Object getAll(User user, DateTime moment);
}
