package com.simbircite.demo.model;

import org.joda.time.DateTime;

import com.simbircite.demo.util.DateUtil;

public class BudgetState {

    private DateTime moment;
    private int payCount = 0;
    private int debtCount = 0;
    private double balance = 0;
    private double unpaid = 0;
    
    public DateTime getMoment() {
        return moment;
    }
    
    public void setMoment(DateTime value) {
        moment = value;
    }
    
    public String getMomentString() {
        return DateUtil.format(moment);
    }
    
    public int getPayCount() {
        return payCount;
    }
    
    public void setPayCount(int value) {
        payCount = value;
    }
    
    public int getDebtCount() {
        return debtCount;
    }
    
    public void setDebtCount(int value) {
        debtCount = value;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double value) {
        balance = value;
    }
    
    public double getUnpaid() {
        return unpaid;
    }
    
    public void setUnpaid(double value) {
        unpaid = value;
    }
}
