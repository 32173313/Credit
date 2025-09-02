package com.reward_project.credit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConverterUtil {
    // 日期的计算都用calendar
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date setTime(int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        int year = getYear();
        int month = getMonth();
        int day = getDay();
        calendar.set(year, month, day, hourOfDay, minute, second);
        Date date = calendar.getTime();
        return date;
    }

    public static String formatDay(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}

