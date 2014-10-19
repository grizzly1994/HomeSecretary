package com.simbircite.demo.service;

import java.util.Collection;
import java.util.LinkedList;

import com.simbircite.demo.model.Debt;
import com.simbircite.demo.model.DebtState;
import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.User;

public class DebtStateService implements EntityListService {
    
    private DebtService debtService;
    
    public DebtStateService(DebtService debtService) {
        this.debtService = debtService;
    }
    
    public Iterable<DebtState> getAll(User user, Moment moment) {
        Collection<DebtState> debtStates = new LinkedList<DebtState>();
        for (Debt debt : debtService.getAll(user, moment)) {
            debtStates.add(new DebtState(debt, moment.getMoment()));
        }
        return debtStates;
    }
}
