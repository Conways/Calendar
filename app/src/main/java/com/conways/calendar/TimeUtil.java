package com.conways.calendar;

import java.util.Calendar;

/**
 * Created by Conways on 2017/3/2.
 */

public class TimeUtil {

    /**
     * 根据时间戳获取当月的天数
     *
     * @param timeStamp
     * @return
     */
    public static int getDayCountsOfOneMonthByTimestamp(long timeStamp) {
        if (timeStamp == 0) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
     * 根据时间戳获取当月第一天是星期几，
     *
     * @param timeStamp
     * @return
     */
    public static int getWeekDayOfFirstDayInOneMonthByTimeStamp(long timeStamp) {
        if (timeStamp == 0) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public static int getWeekDayOfLastDayInOneMonthByTimeStamp(long timeStamp) {
        if (timeStamp == 0) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int lastDay = getDayCountsOfOneMonthByTimestamp(timeStamp);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
