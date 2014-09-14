package com.simbircite.homesecretary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "PERIODIC_TRANSACTIONS")
public class PeriodicTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	int id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	Users user;
	
	@Column(name = "ACCRUAL", nullable = false)
	DateTime accrual; //время когда начислять
	
	@Column(name = "END")
	DateTime end; //конец выплат
	
	@Column(name = "SUMM", nullable = false)
	double summ;
	
	@Column(name = "PERIOD")
	DateTime period; //частота начисления
	
	@Column(name = "PERCENTAGE")
	double percentage; //сколько процентов от суммы начисляется
	
	

	public int getId() {
		return id;
	}
	
	public void setId(int value) {
		id = value;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users value) {
		user = value; 
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
	
	public DateTime getEnd() {
		return end;
	}
	
	public void setEnd(DateTime value) {
		end = value;
	}
	
	public DateTime getPeriod() {
		return period;
	}
	
	public void setPeriod(DateTime value) {
		period = value;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double value) {
		percentage = value;
	}
}
