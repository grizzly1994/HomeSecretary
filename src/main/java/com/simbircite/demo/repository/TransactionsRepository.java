package com.simbircite.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.homesecretary.entity.Transaction;

public interface TransactionsRepository extends PagingAndSortingRepository<Transaction, Integer>{

}
