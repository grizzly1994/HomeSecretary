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
	
	@Column(name = "SUMM")
	double summ;
	
	@Column(name = "ACCRUAL")
	DateTime accrual; //время когда начислять
	
	@Column(name = "PERIOD")
	DateTime period;
	
	@Column(name = "PERCENTAGE")
	double percentage;
	
	@Column(name = "PERCENTAGE_PERIOD")
	DateTime percentagePeriod;	
	
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user; 
	}
	
	public double getSumm() {
		return summ;
	}
	
	public void setSumm(double summ) {
		this.summ = summ;
	}
	
	public DateTime getAccrual() {
		return accrual;
	}
	
	public void setAccrual(DateTime accrual) {
		this.accrual = accrual;
	}
	
	public DateTime getPeriod() {
		return period;
	}
	
	public void setPeriod(DateTime period) {
		this.period = period;
	}
	
	public double getPercentage() {
		return percentage;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public DateTime getPercentagePeriod() {
		return percentagePeriod;
	}
	
	public void setPercentagePeriod(DateTime percentagePeriod) {
		this.percentagePeriod = percentagePeriod;
	}	
}
