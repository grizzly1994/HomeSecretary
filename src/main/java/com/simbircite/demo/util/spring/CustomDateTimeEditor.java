package com.simbircite.demo.util.spring;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class CustomDateTimeEditor extends PropertyEditorSupport {

    DateTimeFormatter formatter = null;

    public CustomDateTimeEditor(DateTimeFormatter formatter) {
        super();
        this.formatter = formatter;
    }

    @Override
    public String getAsText() {
        DateTime value = (DateTime) getValue();
        return (value == null) 
                ? ""
                : value.toString(formatter);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(DateTime.parse(text, formatter));
        } catch (IllegalArgumentException ignore) {
            setValue(null);
        }
    }
}