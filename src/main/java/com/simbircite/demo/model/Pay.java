package com.simbircite.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.simbircite.demo.util.DateUtil;

@Entity
@Table(name = "PAY")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment", nullable = true)
    private String comment;

    @Column(name = "date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime date;

    @Column(name = "balance")
    private double balance;

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

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime value) {
        date = value;
    }

    public String getDateString() {
        return DateUtil.format(date);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double value) {
        balance = value;
    }
}
