package com.fon.hotel.editor;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateEditor extends PropertyEditorSupport {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Date date = null;
        if(text!=null && !text.isEmpty())
        try{
            date = sdf.parse(text);
        }catch(ParseException ex) {
            ex.printStackTrace();
        }
        setValue(date);
    }

    @Override
    public String getAsText() {
        Object object = getValue();
        if(object == null)
            return "";
        Date date = (Date) getValue();
        return sdf.format(date);
    }
}
