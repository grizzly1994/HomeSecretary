package com.simbircite.demo.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TIMESHEET")
public class Timesheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMESHEET_ID", nullable = false)
    private int timesheetId;

    @Column(name = "EMPLOYEE_ID", nullable = false)
    private int employeeId;

    @Column(name = "MINUTES_MON", nullable = false)
    private int minutesMon;

    @Column(name = "MINUTES_TUE", nullable = false)
    private int minutesTue;

    @Column(name = "MINUTES_WED", nullable = false)
    private int minutesWed;

    @Column(name = "MINUTES_THU", nullable = false)
    private int minutesThu;

    @Column(name = "MINUTES_FRI", nullable = false)
    private int minutesFri;

    @Column(name = "MINUTES_SAT", nullable = false)
    private int minutesSat;

    @Column(name = "MINUTES_SUN", nullable = false)
    private int minutesSun;

    @Column(name = "STATUS_CODE")
    private String statusCode;

    @Column(name = "DEPARTMENT_CODE")
    private String departmentCode;

    @Column(name = "PERIOD_ENDING_DATE")
    private Date periodEndingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_CODE", insertable = false, updatable = false)
    private Department department;

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getMinutesMon() {
        return minutesMon;
    }

    public void setMinutesMon(int minutesMon) {
        this.minutesMon = minutesMon;
    }

    public int getMinutesTue() {
        return minutesTue;
    }

    public void setMinutesTue(int minutesTue) {
        this.minutesTue = minutesTue;
    }

    public int getMinutesWed() {
        return minutesWed;
    }

    public void setMinutesWed(int minutesWed) {
        this.minutesWed = minutesWed;
    }

    public int getMinutesThu() {
        return minutesThu;
    }

    public void setMinutesThu(int minutesThu) {
        this.minutesThu = minutesThu;
    }

    public int getMinutesFri() {
        return minutesFri;
    }

    public void setMinutesFri(int minutesFri) {
        this.minutesFri = minutesFri;
    }

    public int getMinutesSat() {
        return minutesSat;
    }

    public void setMinutesSat(int minutesSat) {
        this.minutesSat = minutesSat;
    }

    public int getMinutesSun() {
        return minutesSun;
    }

    public void setMinutesSun(int minutesSun) {
        this.minutesSun = minutesSun;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Date getPeriodEndingDate() {
        return periodEndingDate;
    }

    public void setPeriodEndingDate(Date periodEndingDate) {
        this.periodEndingDate = periodEndingDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
