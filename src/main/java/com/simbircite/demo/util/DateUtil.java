package com.simbircite.demo.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy.MM.dd";

    public static DateTime now() {
        return new DateTime();
    }
    
    public static String getDateFormat() {
        return DATE_FORMAT;
    }
    
    public static String format(DateTime date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(getDateFormat());
        return (date == null) ? "" : fmt.print(date);
    }
}
