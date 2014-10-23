package com.simbircite.secretary.service;

import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.DebtState;
import com.simbircite.secretary.model.User;

public class DebtStateService implements EntityListService {
    
	@Autowired
    private DebtService debtService;
    
    public Iterable<DebtState> getAll(User user, DateTime moment) {
        Collection<DebtState> debtStates = new LinkedList<DebtState>();
        for (Debt debt : debtService.getAll(user, moment)) {
            debtStates.add(new DebtState(debt, moment));
        }
        return debtStates;
    }
}
