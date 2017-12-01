package com.lawyer.manage.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前小时字符串 格式（HH）
     */
    public static String getHour() {
        return formatDate(new Date(), "HH");
    }

    /**
     * 得到当前小时字符串 格式（HH）
     */
    public static String getMinute() {
        return formatDate(new Date(), "mm");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 得到当前是一月中第几个星期串 格式（W）
     */
    public static String getMonthWeekNum() {
        return formatDate(new Date(), "W");
    }

    public static String getWeekOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        if (w == 0) w = 7;
        return w+"";
    }

    /**
     * 得到当前小时字符串 格式（H）
     */
    public static String getHourOfDate() {
        return formatDate(new Date(), "H");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static Date getDateAfter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return c.getTime();
    }

    public static Date getDateBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 取得当前日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1); // Sunday
        return c.getTime();
    }

    /**
     * 取得当前日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();

        c.setTime(date);
        c.add(Calendar.MONTH, 1);//月增加1天
        c.set(Calendar.DAY_OF_MONTH, -1);//日期倒数一日,既得到本月最后一天

        return c.getTime();
    }


    /**
     * 获取当月的前几月
     * num -1 位前一月 不支持大约13个月
     * flag： true 返回月份 ,false 返回年月
     *
     * @param num
     * @param flag
     * @return
     */
    public static String getYearMonth(int num, boolean flag) {
        Date date = new Date();
        String cuDate = DateFormatUtils.format(date, "yyyyMM");
        Calendar cal = Calendar.getInstance();
        int cuM = cal.get(Calendar.MONTH) + 1;
        if (num >= 0) {
            if (flag) {
                return String.valueOf(cuM);
            } else {
                return cuDate;
            }

        }
        int numABS = Math.abs(num);
        if (numABS > 12) {
            if (flag) {
                return String.valueOf(cuM);
            } else {
                return cuDate;
            }
        }
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int rm = month - numABS;
        if (rm <= 0) {
            year--;
        }
        int m = rm == 0 ? 12 : (rm > 0 ? rm : (12 - Math.abs(rm)));
        String mon = m < 10 ? "0" + String.valueOf(m) : String.valueOf(m);
        if (flag) {
            return String.valueOf(m);
        } else {
            return String.valueOf(year) + mon;
        }
    }

    /**
     * 数字月份转换成汉字月份
     *
     * @return
     */
    public static String getChineseMonth(int month) {
        String[] cMonth = new String[]{"零", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        return cMonth[month];
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//        System.out.println("DateUtils.getDateBefore(new Date())=="+DateFormatUtils.format(DateUtils.getDateBefore(new Date()), parsePatterns[1]));
//        System.out.println("DateUtils.getDateAfter(new Date())=="+DateFormatUtils.format(DateUtils.getDateAfter(new Date()), parsePatterns[1]));
//        System.out.println("DateUtils.getFirstDayOfWeek(new Date())=="+DateFormatUtils.format(DateUtils.getFirstDayOfWeek(new Date()), parsePatterns[1]));
//        System.out.println("DateUtils.getLastDayOfWeek(new Date())=="+DateFormatUtils.format(DateUtils.getLastDayOfWeek(new Date()), parsePatterns[1]));
//        System.out.println("DateUtils.getFirstDayOfMonth(new Date())=="+DateFormatUtils.format(DateUtils.getFirstDayOfMonth(new Date()), parsePatterns[1]));
//        System.out.println("DateUtils.getLastDayOfMonth(new Date())=="+DateFormatUtils.format(DateUtils.getLastDayOfMonth(new Date()), parsePatterns[1]));


        System.out.println(Integer.valueOf(getDay()));
        System.out.println(getDate("yyyyMM"));
        System.out.println(getMinute());
    }
}
