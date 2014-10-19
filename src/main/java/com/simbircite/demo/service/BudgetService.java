package com.simbircite.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.joda.time.DateTime;

import com.simbircite.demo.model.BudgetState;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.Pay;
import com.simbircite.demo.model.User;

public class BudgetService implements EntityListService {

    private final PayService payService;
    private final DebtService debtService;
    
    public BudgetService(PayService payService, DebtService debtService) {
        this.payService = payService;
        this.debtService = debtService;
    }
    
    public Iterable<BudgetState> getAll(User user, Moment moment) {
        Iterable<Pay> pays = payService.getAll(user, moment);
        Iterable<Debt> debts = debtService.getAll(user, moment);
        
        BudgetStateService budgetStateService = new BudgetStateService(pays, debts);
        
        DateTime firstPay = null;
        DateTime firstDebtState = null;
        DateTime begin = null;
        
        if (pays.iterator().hasNext()) {
            firstPay = pays.iterator().next().getDate();
        }
        if (debts.iterator().hasNext()) {
            firstDebtState = debts.iterator().next().getDate();
        }
        
        if (firstPay == null) {
            begin = firstDebtState;
        } else if (firstDebtState == null) {
            begin = firstPay;
        } else {
            begin = Collections.min(Arrays.asList(new DateTime[] {firstPay, firstDebtState}));
        }
        
        Collection<BudgetState> budgetStates = new LinkedList<BudgetState>();
        
        for (DateTime date = begin; date.compareTo(moment.getMoment()) != 1; date = date.plusMonths(1)) {
            DateTime month = date.dayOfMonth().withMaximumValue();
            budgetStates.add(budgetStateService.get(month));
        }
        
        return budgetStates;
    }
}