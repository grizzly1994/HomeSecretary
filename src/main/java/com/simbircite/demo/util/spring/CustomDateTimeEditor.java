package com.simbircite.demo.util.spring;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class CustomDateTimeEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    public CustomDateTimeEditor(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getAsText() {
    	DateTime value = (DateTime) getValue();
        return value == null ? "" : value.toString(formatter);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
		setValue(DateTime.parse(text, formatter));
	}
}