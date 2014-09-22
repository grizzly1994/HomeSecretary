package com.simbircite.demo.repository;

import org.springframework.data.repository.*;
import com.simbircite.demo.model.*;

public interface PayRepository extends
		PagingAndSortingRepository<Pay, Integer> {
	
}
