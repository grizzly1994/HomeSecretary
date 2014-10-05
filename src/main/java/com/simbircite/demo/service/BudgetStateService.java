package com.simbircite.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.simbircite.demo.model.BudgetState;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.model.DebtState;
import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.Pay;

public class BudgetStateService implements EntityListService {

    private final PayService payService;
    private final DebtService debtService;
    
    public BudgetStateService(PayService payService, DebtService debtService) {
        this.payService = payService;
        this.debtService = debtService;
    }
    
    public Iterable<BudgetState> getAll(Moment moment) {
        BudgetStates budgetStates = new BudgetStates();
        Iterable<Pay> pays = payService.getAll(moment);
        double balance = 0;
        for (Pay pay : pays) {
            BudgetState budgetState = budgetStates.get(pay.getDate());
            budgetState.setPayCount(budgetState.getPayCount() + 1);
            balance = budgetState.getBalance() + pay.getBalance();
            budgetState.setBalance(balance);
            budgetStates.update(budgetState);
        }
        Iterable<Debt> debts = debtService.get(moment);
        for (Debt debt : debts) {
            DateTime begin = debt.getDate();
            DateTime end = debt.getDeadline();
            for (DateTime date = begin; date.isBefore(end); date = date.plusMonths(1)) {
                BudgetState budgetState = budgetStates.get(date);
                DebtState debtState = new DebtState(date, debt);
                budgetState.setDebtCount(budgetState.getDebtCount() + 1);
                budgetState.setUnpaid(budgetState.getUnpaid() + debtState.getUnpaid());
                budgetStates.update(budgetState);
            }
        }
        return budgetStates.get();
    }
}

class BudgetStates {
    
    Map<DateTime, BudgetState> budgetStates = new HashMap<DateTime, BudgetState>();
    
    public BudgetState get(DateTime date) {
        date = date.dayOfMonth().withMaximumValue();
        BudgetState budgetState;
        if (!budgetStates.containsKey(date)) {
            budgetState = new BudgetState();
            budgetState.setMoment(date);
        }
        else {
            budgetState = budgetStates.get(date);
        }
        return budgetState;
    }
    
    public void update(BudgetState budgetState) {
        budgetStates.put(budgetState.getMoment(), budgetState);
    }
    
    public Iterable<BudgetState> get() {
        return budgetStates.values();
    }
}