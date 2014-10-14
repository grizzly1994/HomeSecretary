package com.simbircite.demo.service;

import org.joda.time.DateTime;

import com.simbircite.demo.model.BudgetState;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.model.DebtState;
import com.simbircite.demo.model.Pay;

public class BudgetStateService {

    private final Iterable<Pay> pays;
    private final Iterable<Debt> debts;
    
    public BudgetStateService(Iterable<Pay> pays, Iterable<Debt> debts) {
        this.pays = pays;
        this.debts = debts;
    }
    
    public BudgetState get(DateTime moment) {
        int payCount = 0;
        int debtCount = 0;
        double total = 0;
        double rest = 0;
        double unpaid = 0;
        
        for (Pay pay : pays) {
            if (pay.getDate().compareTo(moment) == 1) {
                break;
            }
            total += pay.getBalance();
            payCount++;
        }
        
        for (Debt debt : debts) {
            if (debt.getDate().compareTo(moment) == 1) {
                break;
            }
            DebtState debtState = new DebtState(debt, moment);
            unpaid += debtState.getUnpaid();
            rest += debtState.getRest();
            if (debtState.getUnpaid() > 0) {
                debtCount++;
            }
        }
        
        return new BudgetState(moment, payCount, debtCount, total, unpaid, total + rest);
    }
}
