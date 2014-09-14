package com.simbircite.homesecretary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity 
@Table(name = "TRANSACTIONS")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	int id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	Users user;
	
	@NotNull(message = "{validation.date.required}")
    @Past(message = "{validation.date.Past}")
	@Column(name = "DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
	DateTime date;
	
	@Column(name = "SUMM", nullable = false)
	double summ;
	
	@Column(name = "CATEGORY", nullable = false)
	String category;
	
	@Column(name = "COMMENT")
	String comment;
	
	@Column(name = "BALANCE")
	double balance;

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

	public DateTime getDate() {
		return date;
	}
	
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public double getSumm() {
		return summ;
	}
	
	public void setSumm(double summ) {
		this.summ = summ;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
