package com.simbircite.secretary.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.BudgetState;
import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.DebtState;
import com.simbircite.secretary.model.Pay;
import com.simbircite.secretary.model.User;

public class BudgetStateService {

	@Autowired
	private PayService payService;
	
	@Autowired
	private DebtService debtService;
    
    public BudgetState get(User user, DateTime moment) {
    	Iterable<Pay> pays = payService.getAll(user, moment);
    	Iterable<Debt> debts = debtService.getAll(user, moment);
    	
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
