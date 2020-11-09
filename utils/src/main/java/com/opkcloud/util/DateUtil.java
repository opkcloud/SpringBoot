//package com.opkcloud.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//public class DateUtil {
//    public final static String EXCEL_FORMAT = "MM/dd/yyyy";
//    public final static String DEFAULT_FORMAT = "yyyy-MM-dd";
//    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
//    public final static String DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
//    public final static String YEAR_MONTH_FORMAT = "yyyy-MM";
//
//    public static Date parseDate(String sDate, String format) throws ParseException {
//        return new SimpleDateFormat(format).parse(sDate);
//    }
//
//    /**
//     * 设置lenient为false,进行日期强校验，比如2018-02-29不会被接受
//     *
//     * @param sDate
//     * @param dateFormat
//     * @return
//     * @throws ParseException
//     */
//    public static Date parseDateWithLenien(String sDate, String dateFormat) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
//        format.setLenient(false);
//        return format.parse(sDate);
//    }
//
//    public static Date parse(String date) throws ParseException {
//        return new SimpleDateFormat().parse(date);
//    }
//
//    /**
//     *  解析日期类型
//     * @param date
//     * @param pattern  日期格式， 默认  yyyy-MM-dd
//     * @return
//     * @throws ParseException
//     */
//    public static Date parse(String date, String pattern) throws ParseException {
//        if(StringUtil.isNullOrEmpty(pattern)){
//            pattern = DEFAULT_FORMAT;
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        return simpleDateFormat.parse(date);
//    }
//
//    public static Date parseExcelDate(String sDate) throws ParseException {
//        return parseDate(sDate, EXCEL_FORMAT);
//    }
//
//    public static String format(Date date, String format){
//        return new SimpleDateFormat(format).format(date);
//    }
//
//    public static String format(String sDate,String pattern) throws ParseException {
//        Date date = parse(sDate, DEFAULT_FORMAT);
//        return format(date, pattern);
//    }
//
//    public static String format(String sDate, String parsePattern,String pattern) throws ParseException {
//        Date date = parse(sDate, parsePattern);
//        return format(date, pattern);
//    }
//
//    public static String addDayAndFormat(Date sDate, int num, String pattern){
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(sDate);
//        cal.add(Calendar.DATE, num);
//        return format(cal.getTime(), pattern);
//    }
//
//    public static String addDayAndFormat(String sDate, int num, String pattern) throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(parse(sDate));
//        cal.add(Calendar.DATE, num);
//        return format(cal.getTime(), pattern);
//    }
//
//    public static String addDayAndFormat(String sDate, String parsePattern, int num, String pattern) throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(parse(sDate,parsePattern));
//        cal.add(Calendar.DATE, num);
//        return format(cal.getTime(), pattern);
//    }
//
//    public static String addMonthAndFormat(String sDate, int num, String pattern) throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
//        cal.setTime(date);
//        cal.add(Calendar.MONTH, num);
//        return format(cal.getTime(), pattern);
//    }
//
//    public static String addMonthAndFormat(Date date, int num, String pattern) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.add(Calendar.MONTH, num);
//        return format(cal.getTime(), pattern);
//    }
//
//    public static boolean isTheSameDay(String datetime1, String datetime2) throws ParseException {
//        if (StringUtil.isNullOrEmpty(datetime1)) {
//            return false;
//        }
//        if (StringUtil.isNullOrEmpty(datetime2)) {
//            return false;
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date1 = simpleDateFormat.format(simpleDateFormat.parse(datetime1));
//        return date1.equals(simpleDateFormat.format(simpleDateFormat.parse(datetime2)));
//    }
//
//    public static boolean isTheSameDay(Date datetime1, Date datetime2) throws ParseException {
//        if (datetime1 == null) {
//            return false;
//        }
//        if (datetime2 == null) {
//            return false;
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date1 = simpleDateFormat.format(datetime1);
//        return date1.equals(simpleDateFormat.format(datetime2));
//    }
//
//    /**
//     * 指定日期增加天数
//     *
//     * @param sDate
//     * @param num
//     * @return
//     */
//    public static Date addDay(Date sDate, int num) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(sDate);
//        cal.add(Calendar.DATE, num);
//        return cal.getTime();
//    }
//
//    /**
//     * 格式化Excel时间,读取excel时间为距离1900的天数
//     *
//     * @param day
//     * @return yyyy-MM-dd
//     */
//    public static Date formatExcelDate(int day) {
//        Calendar calendar = new GregorianCalendar(1900,0,-1);
//        Date gregorianDate = calendar.getTime();
//        return DateUtil.addDay(gregorianDate, day);
//    }
//
//    /**
//     * 获取mysql存储时间上限
//     *
//     * @return
//     */
//    public static Date mysqlMaxDateTime() {
//        try {
//            return parse("9999-12-31 23:59:59", DATETIME_FORMAT);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return new Date();
//    }
//
//    /**
//     * 获取excel最早存储时间限制
//     *
//     * @return
//     */
//    public static Date excelMinDateTime() {
//        try {
//            return parse("2000-01-01 00:00:00", DATETIME_FORMAT);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return new Date();
//    }
//
//    public static int getYear(Date date){
//        Calendar ca = Calendar.getInstance();
//        ca.setTime(date);
//        return ca.get(Calendar.YEAR);
//    }
//
//    /**
//     * 获取实际月份
//     * @param date
//     * @return
//     */
//    public static int getMonth(Date date){
//        Calendar ca = Calendar.getInstance();
//        ca.setTime(date);
//        return ca.get(Calendar.MONTH) + 1;
//    }
//
//    /**
//     * 获取某年第一天日期
//     *
//     * @param date
//     * @return
//     */
//    public static Date getFirstDateOfYear(Date date){
//        int year = getYear(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(Calendar.YEAR, year);
//        return calendar.getTime();
//    }
//
//    /**
//     * 获取某年最后一天日期
//     * @param date 年份
//     * @return Date
//     */
//    public static Date getLastDateOfYear(Date date){
//        int year = getYear(date);
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(Calendar.YEAR, year);
//        calendar.roll(Calendar.DAY_OF_YEAR, -1);
//        Date currYearLast = calendar.getTime();
//        return currYearLast;
//    }
//
//    public static String getFirstFormatDateOfYear(Date date){
//        Date first = getFirstDateOfYear(date);
//        return format(first, DateUtil.DEFAULT_FORMAT);
//    }
//
//    /**
//     * 获取某年最后一天日期 (yyyy-MM-dd)
//     * @param date 年份
//     * @return Date
//     */
//    public static String getLastFormatDateOfYear(Date date){
//        Date last = getLastDateOfYear(date);
//        return format(last, DateUtil.DEFAULT_FORMAT);
//    }
//
//}
