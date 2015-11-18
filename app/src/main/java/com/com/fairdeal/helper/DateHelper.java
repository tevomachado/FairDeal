package com.com.fairdeal.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Carlos Eduardo on 18/11/2015.
 */
public class DateHelper {

    public static String dateToDbFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
