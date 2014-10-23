package com.simbircite.secretary.model;

import org.joda.time.DateTime;

import com.simbircite.secretary.util.DateUtil;

public class Period {

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
