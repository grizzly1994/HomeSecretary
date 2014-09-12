package com.simbircite.homesecretary.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "USER")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	int id;
	
	@Column(name = "NAME", nullable = false)
	String name;
	
	@Column(name = "PASSWORD", nullable = false)
	String password;
	
	@Column(name = "EMAIL", nullable = false)
	String email;
	
	@Column(name = "BIRTHDAY", nullable = false)
	DateTime birthday;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Transaction> transactions;
	
	@OneToMany
	Set<PeriodicTransactions> periodicTransactions;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public DateTime getBirthday() {
		return birthday;
	}
	
	public void setBirthDay(DateTime birthday) {
		this.birthday = birthday;
	}
	
	public Set<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public Set<PeriodicTransactions> getPeriodicTransactions() {
		return periodicTransactions;
	}
	
	public void setPeriodicTransactions(Set<PeriodicTransactions> periodicTransactions) {
		this.periodicTransactions = periodicTransactions;
	}
}
