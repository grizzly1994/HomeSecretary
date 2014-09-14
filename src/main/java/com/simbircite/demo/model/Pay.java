package com.simbircite.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "PAY")
public class Pay {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "balance")
	private double balance;
	
	public int getId() {
		return id;
	}
	
	public void setId(int value) {
		id = value;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String value) {
		date = value;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double value) {
		balance = value;
	}
}
