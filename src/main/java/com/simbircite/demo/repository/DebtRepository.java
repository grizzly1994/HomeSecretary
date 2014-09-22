package com.simbircite.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.demo.model.Debt;

public interface DebtRepository
		extends PagingAndSortingRepository<Debt, Integer> {

}
