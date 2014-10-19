package com.simbircite.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.simbircite.demo.util.DateUtil;

@Entity
@Table(name = "DEBT")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    
    @Column(name = "comment")
    private String comment;

    @Column(name = "balance")
    private double balance;

    @Column(name = "date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime date;

    @Column(name = "deadline")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime deadline;

    @Column(name = "repay")
    private double repay;

    @Column(name = "frequency")
    private int frequency;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public User getUser() {
		return user;
	}

	public void setUser(User value) {
		user = value;
	}
    
    public String getComment() {
        return comment;
    }

    public void setComment(String value) {
        comment = value;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double value) {
        balance = value;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime value) {
        date = value;
    }

    public String getDateString() {
        return DateUtil.format(date);
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

    public double getRepay() {
        return repay;
    }

    public void setRepay(double value) {
        repay = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int value) {
        frequency = value;
    }
}
