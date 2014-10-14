package com.simbircite.demo.service;

import java.util.Collection;
import java.util.LinkedList;

import com.simbircite.demo.model.Debt;
import com.simbircite.demo.model.DebtState;
import com.simbircite.demo.model.Moment;

public class DebtStateService implements EntityListService {
    
    private DebtService debtService;
    
    public DebtStateService(DebtService debtService) {
        this.debtService = debtService;
    }
    
    public Iterable<DebtState> getAll(Moment moment) {
        Collection<DebtState> debtStates = new LinkedList<DebtState>();
        for (Debt debt : debtService.getAll(moment)) {
            debtStates.add(new DebtState(debt, moment.getMoment()));
        }
        return debtStates;
    }
}
