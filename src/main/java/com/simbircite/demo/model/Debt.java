package com.simbircite.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.simbircite.demo.util.DateUtil;

@Entity
@Table(name = "PERIODIC_TRANSACTIONS")
public class Debt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	
	@Column(name = "COMMENT", nullable = true)
	private String comment;
	
	@Column(name = "ACCRUAL")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
	DateTime accrual; //время когда начислять
	
	@Column(name = "DEADLINE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
	DateTime deadline; //конец выплат
	
	@Column(name = "SUMM")
	double summ;
	
	@Column(name = "PERIOD")
	int period; //частота начисления
	
	@Column(name = "PERCENTAGE")
	double percentage; //сколько процентов от суммы начисляется
	
	public int getId() {
		return id;
	}
	
	public void setId(int value) {
		id = value;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String value) {
		comment = value;
	}
	
	public double getSumm() {
		return summ;
	}
	
	public void setSumm(double value) {
		summ = value;
	}
	
	public DateTime getAccrual() {
		return accrual;
	}
	
	public void setAccrual(DateTime value) {
		accrual = value;
	}
	
	public String getAccrualString() {
        return DateUtil.format(accrual);
    }
	
	public DateTime getDeadline() {
		return deadline;
	}	
	
	public void setDeadline(DateTime value) {
		deadline = value;
	}
	
	public String getDeadlineString() {
        return DateUtil.format(deadline);
    }
	
	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(int value) {
		period = value;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double value) {
		percentage = value;
	}
	
	// Единичный платеж
	
	public double getPay() {
		return summ * percentage / 100;
	}
	
	// Количество необходимых платежей
	
	public int getInterval() {
		return Months.monthsIn(new Interval(accrual, deadline)).getMonths();
	}
	
	public int getPayCount() {
		return getInterval() / period;
	}
	
	public double getTotal() {
		return getPay() * getPayCount();
	}
	
	// Количество совершенных платежей
	
	public int getCurrentInterval() {
		return Months.monthsIn(new Interval(accrual, new DateTime())).getMonths();
	}
	
	public int getCurrentPayCount() {
		return getCurrentInterval() / period;
	}
	
	public double getComplete() {
		return getPay() * getCurrentPayCount();
	}
	
	// Осталось оплатить
	
	public double getBalance() {
		return getTotal() - getComplete();
	}
}
