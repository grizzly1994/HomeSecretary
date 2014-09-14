package com.simbircite.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.homesecretary.entity.Users;

public interface UsersRepository extends PagingAndSortingRepository<Users, Integer>{

}
