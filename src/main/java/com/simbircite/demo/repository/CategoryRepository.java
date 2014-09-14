package com.simbircite.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.simbircite.homesecretary.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{

}
