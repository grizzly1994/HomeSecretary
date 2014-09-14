package com.simbircite.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.homesecretary.entity.PeriodicTransaction;

public interface PeriodicTransactionRepository 
			extends PagingAndSortingRepository<PeriodicTransaction, Integer> {

}
