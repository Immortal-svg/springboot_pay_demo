package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
    /**
     * Logger for this class
     */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat FORMAT_DATE_TIME2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public static final SimpleDateFormat FORMAT_DATE_TIME3 = new SimpleDateFormat("yyyy-MM-dd HH");

    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");

    public static final SimpleDateFormat FORMAT_MONTH = new SimpleDateFormat("yyyy-MM");

    public static final SimpleDateFormat FORMAT_MONTH1 = new SimpleDateFormat("yyyyMM");

    public static final SimpleDateFormat HOUR = new SimpleDateFormat("HH");

    public static final SimpleDateFormat DAY = new SimpleDateFormat("dd");

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_HM=new SimpleDateFormat("yyyyMMddHHmmss");

    public DateUtil() {
    }

    public static String date2Str(Date date, SimpleDateFormat dateFmt) {
        if (date == null)
            return "";
        return dateFmt.format(date);
    }

    public static Date str2Date(String dateStr, SimpleDateFormat dateFmt) {
        if (dateStr != null)
            try {
                return dateFmt.parse(dateStr);
            } catch (ParseException localParseException) {
            }
        return null;
    }

    public static String dateStrFomater(String dateStr, SimpleDateFormat fromDateFmt, SimpleDateFormat toDateFmt) {
        if (dateStr != null)
            try {
                return toDateFmt.format(fromDateFmt.parse(dateStr));
            } catch (Exception localException) {
            }
        return "";
    }

    public static String parseFormatLongToStr(long time, String format) {
        SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat(format);
        return long2DateStr(time, FORMAT_DATE_TIME);
    }

    public static String long2DateStr(long time, SimpleDateFormat t) {
        java.sql.Date date = new java.sql.Date(time);
        try {
            return t.format(date);
        } catch (Exception e) {
        }
        return "";
    }

    public static Date parseFormatLongToDate(long time, String format) {
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:dd:ss";
        }
        SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat(format);
        return parseDateString(long2DateStr(time, FORMAT_DATE_TIME), format);
    }

    public static Timestamp parseFormatString(String str, String format) {
        if (str == null || str.equals(""))
            return null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            Date dt = df.parse(str);
            Timestamp timestamp = new Timestamp(dt.getTime());
            return timestamp;
        } catch (Exception pe) {
        }
        Timestamp timestamp3;
        try {
            Timestamp timestamp1 = Timestamp.valueOf(str);
            return timestamp1;
        } catch (Exception e) {
            timestamp3 = null;
        }
        return timestamp3;
    }

    public static Date parseDateString(String str, String format) {
        if (str == null || str.equals(""))
            return null;
        Date dt = null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            dt = df.parse(str);

        } catch (Exception pe) {
        }

        return dt;
    }

    public static boolean isBeforeOrEqual(Date date1, Date date2) {
        if (date1.compareTo(date2) > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Timestamp parseLongString(String str) {
        if (str == null || str.equals(""))
            return null;
        try {
            long time = Long.parseLong(str);
            Timestamp timestamp = new Timestamp(time);
            return timestamp;
        } catch (Exception ex) {
            return null;
        }

    }

    public static String formatDate(Date dt, String format) {
        if ((dt == null) || format == null) {
            return "";
        }
        String strDate = "";
        String s1;
        try {
            SimpleDateFormat DATA_FORMAT = new SimpleDateFormat(format);
            strDate = DATA_FORMAT.format(dt);
            String s = strDate;
            return s;
        } catch (Exception e) {
            s1 = null;
        }
        return s1;
    }

    public static String getAdjustDate(Date date, int field, int amount, String format) {
        if ((date == null) || format == null) {
            return "";
        }
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        can.add(field, amount);
        Date newDate = can.getTime();
        String dateStr = formatDate(newDate, format);
        return dateStr;
    }

    public static String format(Date date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }

    public static Date getAdjustDate(Date date, int field, int amount) {
        if ((date == null)) {
            return null;
        }
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        can.add(field, amount);
        Date newDate = can.getTime();
        return newDate;
    }

    public static long getBetweenDiffDay(Date date1, Date date2, boolean isAbs) throws Exception {
        long date1Value = date1.getTime();
        long date2Value = date2.getTime();
        long diff = (date1Value - date2Value) / (24 * 3600 * 1000);
        if (isAbs) {
            return Math.abs(diff);
        } else {
            return diff;

        }

    }

    public static long getBetweenDiffHour(Date date1, Date date2, boolean isAbs) throws Exception {
        long date1Value = date1.getTime();
        long date2Value = date2.getTime();
        long diff = (date1Value - date2Value) / (3600 * 1000);
        if (isAbs) {
            return Math.abs(diff);
        } else {
            return diff;
        }
    }

    public static long getBetweenDiffMinutes(Date date1, Date date2, boolean isAbs) throws Exception {
        long date1Value = date1.getTime();
        long date2Value = date2.getTime();
        long diff = (date1Value - date2Value) / (60 * 1000);
        if (isAbs) {
            return Math.abs(diff);
        } else {
            return diff;
        }
    }

    public static Date getMonthEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        if (can.get(Calendar.DAY_OF_MONTH) == can.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            can.add(Calendar.MONTH, 1);
        }
        can.set(Calendar.DAY_OF_MONTH, can.getActualMaximum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);

        return can.getTime();
    }

    public static Date getCurrentMonthBegin(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        can.set(Calendar.DAY_OF_MONTH, can.getActualMinimum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 00);
        can.set(Calendar.MINUTE, 00);
        can.set(Calendar.SECOND, 00);

        return can.getTime();
    }

    public static Date getCurrentMonthEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        can.set(Calendar.DAY_OF_MONTH, can.getActualMaximum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);

        return can.getTime();
    }

    public static Date getMonthEnd() {
        Date date = new Date();
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        if (can.get(Calendar.DAY_OF_MONTH) == can.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            can.add(Calendar.MONTH, 1);
        }
        can.set(Calendar.DAY_OF_MONTH, can.getActualMaximum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);

        return can.getTime();
    }

    public static Date getWeekEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        if (1 == can.get(Calendar.DAY_OF_WEEK)) {
            can.add(Calendar.WEEK_OF_MONTH, 1);
        }
        can.set(Calendar.DAY_OF_WEEK, can.getActualMaximum(Calendar.DAY_OF_WEEK));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);
        can.add(Calendar.DAY_OF_MONTH, 1);
        return can.getTime();
    }

    public static Date getTomorrowEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);
        can.add(Calendar.DAY_OF_MONTH, 1);
        return can.getTime();
    }

    public static Date getYesterdayEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);
        can.add(Calendar.DAY_OF_MONTH, -1);
        return can.getTime();
    }

    public static Date getAppointDateByOffset(Date date, int hourOffset, int dateOffset, int startOrEnd) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        int hourOfDay = hourOffset;
        int minute = 0;
        int second = 0;
        if (startOrEnd == 1) {
            hourOfDay = 23;
            minute = 59;
            second = 59;
        } else if (startOrEnd == 2) {
            hourOfDay = hourOffset;
            minute = 0;
            second = 0;
        }

        can.set(Calendar.HOUR_OF_DAY, hourOfDay);
        can.set(Calendar.MINUTE, minute);
        can.set(Calendar.SECOND, second);
        can.add(Calendar.DAY_OF_MONTH, dateOffset);
        return can.getTime();
    }

    public static Date getAppointMinByOffset(Date date, int minOffset) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        // int hourOfDay = hourOffset;
        int minute = minOffset;
        // int second = 0;

        // can.set(Calendar.HOUR_OF_DAY, hourOfDay);
        can.set(Calendar.MINUTE, minute);
        // can.set(Calendar.SECOND, second);
        // can.add(Calendar.DAY_OF_MONTH, dateOffset);
        return can.getTime();
    }

    /**
     * 返回当日的开始时间或结束时间
     *
     * @param type 1为开始时间 2为结束时间
     * @return
     */
    public static Date getToday(Date date, int type) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        int hourOfDay = 0;
        int minute = 0;
        int second = 0;
        if (type == 2) {
            hourOfDay = 23;
            minute = 59;
            second = 59;
        } else if (type == 1) {
            hourOfDay = 0;
            minute = 0;
            second = 0;
        }
        can.set(Calendar.HOUR_OF_DAY, hourOfDay);
        can.set(Calendar.MINUTE, minute);
        can.set(Calendar.SECOND, second);
        return can.getTime();
    }

    public static Date getWeekEnd() {
        Date date = new Date();
        Calendar can = Calendar.getInstance();
        can.setTime(date);

        if (1 == can.get(Calendar.DAY_OF_WEEK)) {
            can.add(Calendar.WEEK_OF_MONTH, 1);
        }
        can.set(Calendar.DAY_OF_WEEK, can.getActualMaximum(Calendar.DAY_OF_WEEK));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);
        can.add(Calendar.DAY_OF_MONTH, 1);
        return can.getTime();
    }

    public static String formatTime(Timestamp dt, String format) {
        if ((dt == null) || format == null) {
            return "";
        }
        String strDate = "";
        String s1;
        try {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
            strDate = DATE_FORMAT.format(dt);
            String s = strDate;
            return s;
        } catch (Exception e) {
            s1 = null;
        }
        return s1;
    }

    public static Date getDay(Date d, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int td = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, td + n);
        Date towDay = c.getTime();
        return towDay;
    }

//    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        str2Date(today + " " + "15:35:00", FORMAT_DATE_TIME).getTime();
//        System.out.println(timestamp.getTime() > str2Date(today + " " + "15:35:00", FORMAT_DATE_TIME).getTime());
//
//
//
//    }

    public static List<String> getPreMonth(Date d, int count) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < count; i++) {
            Date dd = getAppointDateByOffset(getCurrentMonthBegin(d), 0, -1, 2);
            d = dd;
            list.add(formatDate(dd, "yyyy-MM"));
        }
        return list;
    }

    public static List<String> getPreMonth(Date d, int count, String format) {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < count; i++) {
            Date dd = getAppointDateByOffset(getCurrentMonthBegin(d), 0, -1, 2);
            d = dd;
            list.add(formatDate(dd, format));
        }
        return list;
    }


    /**
     * 获取前N天日期
     *
     * @param n
     * @return
     */
    public static String getPreNDay(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        date = calendar.getTime();
        return sdf.format(date);
    }


    public static String StringDate(String strDate) {
        String dateTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        String strDt = null;
        String str = dateTime.split("-")[2];
        Integer time = (Integer.parseInt(str)) - Integer.parseInt(strDate);
        strDt = dateTime.split("-")[0] + "-" + dateTime.split("-")[1] + "-" + time;
        return strDt;
    }
    
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
    
    public static String getLackDays(List<String> dateall,List<String> date) {
    	StringBuffer days= new StringBuffer();
    	for(int i=0;i<dateall.size();i++) {
    		String day = dateall.get(i);
    		if(!date.contains(day)){
    			days.append(day).append(",");
    		}
    	}
        return days.toString();
    }
    
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId  = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static String getAtPersent(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String thisDate=SIMPLE_DATE_FORMAT_HM.format(date);
        return thisDate;
    }

    public static String getBeformTime(int min){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, min);
        Date date = cal.getTime();
        String thisDate=SIMPLE_DATE_FORMAT_HM.format(date);
        return thisDate;
    }
}
