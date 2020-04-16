package com.liujx.onlineshop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lenovo
 */
public class DateUtil {

    public static final String MOUTH_DAY = "MM-dd";
    public static final String YEAR_MOUTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MOUTH_DAY_TIME = "yyyy-MM-dd hh:mm:ss";
    private static final long ONE_DAY = 86400000L;
    private static SimpleDateFormat simpleDateFormat;

    public static String  format(Date date, String type){
        simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }

    public Date today(){
        return new Date();
    }

    public Date yesterday(){
        return calculateDay(-1L);
    }

    public Date tomorrow(){
        return calculateDay(1L);
    }

    public Date calculateDay(Long day){
        Date today = new Date();
        return new Date(today.getTime() + (day * ONE_DAY));
    }

    public static Date conversion(String date, String type){
        simpleDateFormat = new SimpleDateFormat(type);
        Date result = null;
        try {
            result = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            result = new Date();
        }

        return result;
    }

    public static List<Date> foreach(Date startTime, Date endTime){
        Date date = startTime;
        List<Date> result = new ArrayList<>();
        while (date.getTime() < endTime.getTime()){
            result.add(date);
            date = new Date(date.getTime() + ONE_DAY);
        }

        return result;
    }


    public static void main(String[] args) {
        DateUtil dateUtil = new DateUtil();
        Date ten = dateUtil.calculateDay(-10L);
        Date today = dateUtil.today();
        System.out.println(ten);
        System.out.println(today);
        System.out.println(foreach(ten, today));

    }
}
