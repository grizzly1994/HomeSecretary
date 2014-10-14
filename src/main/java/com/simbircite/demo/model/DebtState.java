package com.simbircite.demo.model;

import java.util.Arrays;
import java.util.Collections;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;

public class DebtState {

    private final Debt debt;
    private final DateTime date;

    public DebtState(Debt debt, DateTime date) {
        this.debt = debt;
        this.date = date;
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
        date = Collections.min(Arrays.asList(new DateTime[] {date, getDeadline()}));
        Interval interval = new Interval(getDate(), date);
        int period = Months.monthsIn(interval).getMonths();
        return period / getFrequency() * getRepay();
    }
    
    public double getTotal() {
        return getTotal(getDeadline());
    }
    
    public double getUnpaid() {
        return getTotal() - getTotal(date);
    }
    
    public double getRest() {
        return getBalance() - getTotal(date);
    }
}
