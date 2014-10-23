package com.simbircite.secretary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.simbircite.secretary.util.DateUtil;

@Entity
@Table(name = "PAY")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @Column(name = "comment", nullable = false)
    @NotNull(message = "{validation.empty}")
    private String comment;

    @Column(name = "date", nullable = false)
    @NotNull(message = "{validation.empty}")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    private DateTime date;

    @Column(name = "balance", nullable = false)
    @NotNull(message = "{validation.empty}")
    private Double balance;

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

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime value) {
        date = value;
    }

    public String getDateString() {
        return DateUtil.format(date);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double value) {
        balance = value;
    }
}
