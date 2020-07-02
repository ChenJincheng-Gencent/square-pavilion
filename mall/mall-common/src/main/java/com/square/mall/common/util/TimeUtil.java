package com.square.mall.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Slf4j
public class TimeUtil {

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(TimePatternConstant.YYYY_MM_DD_HH_MM_SS);

    /**
     * 时间格式 yyyy-MM-dd
     */
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(TimePatternConstant.YYYY_MM_DD);

    /**
     * 时间格式 HH:mm:ss
     */
    private static final DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern(TimePatternConstant.HH_MM_SS);

    /**
     * 时间格式 HH:mm
     */
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern(TimePatternConstant.HH_MM);

    /**
     * 获取今天的日期，格式为yyyy-MM-dd，例如2018-06-21
     *
     * @return String
     */
    public static String getCurrentDate() {

        return YYYY_MM_DD.format(LocalDate.now());

    }


    /**
     * 四舍五入取整LocalTime
     *
     * @param localTime 时间
     * @return LocalTime
     */
    public static LocalTime roundLocalTime(LocalTime localTime) {

        if (null == localTime) {
            return null;
        }
        long seconds = localTime.getSecond();
        if (seconds >= CommonConstant.THIRTY) {
            return localTime.plusSeconds(CommonConstant.SECONDS_PER_MINUTE - seconds);
        } else {
            return localTime.minusSeconds(seconds);
        }
    }

    /**
     * 获取当前时间，格式为"yyyy-MM-dd HH:mm:ss"，例如2018-06-28 11:36:30
     *
     * @return String
     */
    public static LocalDateTime getLocalTimeToLocalDateTime(LocalTime localTime) {

        LocalDate localDate = LocalDate.now();
        return LocalDateTime.of(localDate, localTime);

    }

    /**
     * 将时间字符串转成LocalTime格式，格式为"HH:mm"，例如"05:30"
     *
     * @param str 时间字符串
     * @return LocalTime
     */
    public static LocalTime getStrToLocalTime(String str) {

        if (StringUtil.isBlank(str)) {
            return null;
        }

        try {
            return LocalTime.parse(str, HH_MM);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage(), e);
        }

        return null;

    }

    /**
     * 将时间转成字符串格式，格式为"HH:mm"，例如"05:30"
     *
     * @param localDateTime 时间
     * @return String
     */
    public static String getLocalDateTimeToHm(LocalDateTime localDateTime) {

        if (null == localDateTime) {
            return null;
        }

        try {
            return localDateTime.format(HH_MM);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 将时间字符串转成LocalDateTime格式，格式为"yyyy-MM-dd HH:mm:ss"，例如2018-06-28 11:36:30
     *
     * @param str 时间字符串
     * @return 时间
     */
    public static LocalDateTime getStrToLocalDateTime(String str) {

        if (StringUtil.isBlank(str)) {
            return null;
        }

        try {
            return LocalDateTime.parse(str, YYYY_MM_DD_HH_MM_SS);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage(), e);
        }

        return null;

    }

    /**
     * 获取当前时间，格式为"yyyy-MM-dd HH:mm:ss"，例如2018-06-28 11:36:30
     *
     * @return String
     */
    public static String getCurrentLocalDateTime() {

        return YYYY_MM_DD_HH_MM_SS.format(LocalDateTime.now());

    }


    /**
     * 获取当前时间，格式为"HH:mm:ss"，例如11:36:30
     *
     * @return String
     */
    public static String getCurrentLocalTime() {

        return HH_MM_SS.format(LocalTime.now());

    }


    /**
     * 获取两个LocalDateTime之间的秒数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return long
     */
    public static long getDurationSeconds(LocalDateTime start, LocalDateTime end) {

        if (null == start || null == end) {
            return 0;
        }

        Duration duration = Duration.between(start, end);

        return duration.toMillis() / CommonConstant.MILLISECONDS_PER_SECOND;

    }

    /**
     * 获取两个LocalDateTime之间的分钟数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 分钟数
     */
    public static long getDurationMinutes(LocalDateTime start, LocalDateTime end) {

        if (null == start || null == end) {
            return 0;
        }
        Duration duration = Duration.between(start, end);

        return duration.toMinutes();

    }

    /**
     *  获取本月最后一天23点59分59秒
     *
     * @return 本月最后一天23点59分59秒
     */
    public static LocalDateTime getMonthLastDayLastTime() {

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(lastDay.toLocalDate(), LocalTime.MAX);

    }

    /**
     *  获取本月最后一天23点59分59秒
     *
     * @return 本月最后一天23点59分59秒
     */
    public static Date getMonthLastDayLastTime2Date() {

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime time = LocalDateTime.of(lastDay.toLocalDate(), LocalTime.MAX);
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * LocalDateTime转为Date
     *
     * @param localDateTime 时间
     * @return 时间
     */
    public static Date getLocalDateTimeToDate(LocalDateTime localDateTime) {

        if (null == localDateTime) {
            return null;
        }

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * 获取2099年12月31日23点59分59秒
     *
     * @return 2099年12月31日23点59分59秒
     */
    public static LocalDateTime getLastDayLastTime() {
        return LocalDateTime.of(2099, 12, 31, 23, 59, 59);
    }


    /**
     * 获取2099年12月31日23点59分59秒
     *
     * @return 2099年12月31日23点59分59秒
     */
    public static Date getLastDayLastTimeToDate() {
        LocalDateTime time = LocalDateTime.of(2099, 12, 31, 23, 59, 59);
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  根据格式化方式将时间字符串转成Date
     *
     * @param timeStr 时间字符串
     * @param pattern 格式化方式
     * @return 时间
     */
    public static Date getTimeStrToDate(String timeStr, String pattern) throws ParseException {

        if (StringUtil.isBlank(timeStr) || StringUtil.isBlank(pattern)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(timeStr);
    }


}
