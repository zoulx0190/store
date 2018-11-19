package com.shop.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @ Description：
 * @ Author     ： zlx
 * @ Date       ： Created in  2018/11/17 2:14
 * @ Modified By：
 */
public class DateTimeUtil {
    public static final String STANDRRD_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static Date str2Date(String dateStr, String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);

        return dateTime.toDate();
    }

    public static String date2Str(Date date,String formatStr){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date str2Date(String dateStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDRRD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);

        return dateTime.toDate();
    }

    public static String date2Str(Date date){
        if(date==null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime=new DateTime(date);
        return dateTime.toString(STANDRRD_FORMAT);
    }

}
