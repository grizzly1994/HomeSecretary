package com.simbircite.secretary.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.BudgetState;
import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.Pay;
import com.simbircite.secretary.model.User;

public class BudgetService implements EntityListService {
	
	@Autowired
    private PayService payService;
	
	@Autowired
    private DebtService debtService;
    
	@Autowired
	private BudgetStateService budgetStateService;
	
    public Iterable<BudgetState> getAll(User user, DateTime moment) {
        Iterable<Pay> pays = payService.getAll(user, moment);
        Iterable<Debt> debts = debtService.getAll(user, moment);
        
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
        
        for (DateTime date = begin; date.compareTo(moment) != 1; date = date.plusMonths(1)) {
            DateTime month = date.dayOfMonth().withMaximumValue();
            budgetStates.add(budgetStateService.get(user, month));
        }
        
        return budgetStates;
    }
}