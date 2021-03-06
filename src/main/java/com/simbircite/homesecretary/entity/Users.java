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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	
	@NotNull(message = "{validation.user.birth.NotNull}")
    @Past(message = "{validation.user.birth.Past}")
	@Column(name = "BIRTHDAY", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
	DateTime birthday;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<Transaction> transactions;
	
	@OneToMany
	Set<PeriodicTransaction> periodicTransactions;
	
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
	
	public Set<PeriodicTransaction> getPeriodicTransactions() {
		return periodicTransactions;
	}
	
	public void setPeriodicTransactions(Set<PeriodicTransaction> periodicTransactions) {
		this.periodicTransactions = periodicTransactions;
	}
}
