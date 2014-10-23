package com.simbircite.secretary.util.spring;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    public DateTimeEditor(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getAsText() {
    	DateTime value = (DateTime) getValue();
        return value == null ? "" : value.toString(formatter);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	try {
    		setValue(DateTime.parse(text, formatter));
    	} catch (Throwable error) {
    		setValue(null);
    	}
	}
}