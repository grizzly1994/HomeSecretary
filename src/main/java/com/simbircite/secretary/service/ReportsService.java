package com.simbircite.secretary.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.Pay;

public class ReportsService {

	@Autowired
	private PayService pays;

	@Autowired
	private DebtService debts;
	
	@Autowired
	private BudgetService budget;

	@Autowired
	private DebtStateService debtStates;
	
	private static final String PAY = "pay";
	private static final String DEBT = "debt";
	private static final String BUDGET = "budget";
	
	public Object getEntity(String report) {
		if (PAY.equals(report)) {
			return new Pay();
		}
		if (DEBT.equals(report)) {
			return new Debt();
		}
		return null;
	}

	public EntityService getService(String report) {
		if (PAY.equals(report)) {
			return pays;
		}
		if (DEBT.equals(report)) {
			return debts;
		}
		return null;
	}

	public EntityListService getListService(String report) {
		if (PAY.equals(report)) {
			return pays;
		}
		if (DEBT.equals(report)) {
			return debtStates;
		}
		if (BUDGET.equals(report)) {
			return budget;
		}
		return null;
	}
}
