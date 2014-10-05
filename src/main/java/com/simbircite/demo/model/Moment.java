package com.simbircite.demo.model;

import org.joda.time.DateTime;

import com.simbircite.demo.util.DateUtil;

public class Moment {

    private DateTime moment;

    public DateTime getMoment() {
        return moment;
    }

    public void setMoment(DateTime value) {
        moment = value;
    }

    public String getMomentString() {
        return DateUtil.format(moment);
    }
}
