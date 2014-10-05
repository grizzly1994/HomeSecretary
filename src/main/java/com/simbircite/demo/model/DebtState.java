package com.simbircite.demo.model;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;

public class DebtState {

    private final DateTime date;
    private final Debt debt;

    public DebtState(DateTime date, Debt debt) {
        this.date = date;
        this.debt = debt;
    }

    public int getId() {
        return debt.getId();
    }

    public String getComment() {
        return debt.getComment();
    }

    public double getBalance() {
        return debt.getBalance();
    }

    public DateTime getDate() {
        return debt.getDate();
    }

    public String getDateString() {
        return debt.getDateString();
    }

    public DateTime getDeadline() {
        return debt.getDeadline();
    }

    public String getDeadlineString() {
        return debt.getDeadlineString();
    }

    public double getRepay() {
        return debt.getRepay();
    }
    
    public int getFrequency() {
        return debt.getFrequency();
    }

    public double setFrequency() {
        return debt.getRepay();
    }

    private double getTotal(DateTime date) {
        Interval interval = new Interval(date, getDeadline());
        int period = Months.monthsIn(interval).getMonths();
        return period / getFrequency() * getRepay();
    }
    
    public double getTotal() {
        return getTotal(getDate());
    }
    
    public double getUnpaid() {
        return getTotal() - getTotal(date);
    }
}
