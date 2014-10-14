package com.simbircite.demo.model;

import org.joda.time.DateTime;

import com.simbircite.demo.util.DateUtil;

public class BudgetState {

    private DateTime moment;
    private int payCount = 0;
    private int debtCount = 0;
    private double total = 0;
    private double unpaid = 0;
    private double balance = 0;


    public BudgetState(DateTime moment, int payCount, int debtCount, double total, double unpaid, double balance) {
        this.moment = moment;
        this.payCount = payCount;
        this.debtCount = debtCount;
        this.total = total;
        this.balance = balance;
        this.unpaid = unpaid;
    }

    public DateTime getMoment() {
        return moment;
    }

    public String getMomentString() {
        return DateUtil.format(moment);
    }

    public int getPayCount() {
        return payCount;
    }
    
    public int getDebtCount() {
        return debtCount;
    }

    public double getTotal() {
        return total;
    }
    
    public double getUnpaid() {
        return unpaid;
    }
    
    public double getBalance() {
        return balance;
    }
}
