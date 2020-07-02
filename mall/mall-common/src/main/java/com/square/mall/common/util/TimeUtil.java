package com.square.mall.common.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author Gencent
 * @date 2019/8/20
 */
public class TimeUtil {

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 时间格式 yyyy-MM-dd
     */
    private static final DateTimeFormatter DF_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 时间格式 HH:mm:ss
     */
    private static final DateTimeFormatter DF_HMS = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 时间格式 HH:mm
     */
    private static final DateTimeFormatter DF_HM = DateTimeFormatter.ofPattern("HH:mm");


    /**
     * 获取今天的日期，格式为yyyy-MM-dd，例如2018-06-21
     *
     * @return String
     */
    public static String getCurrentDate() {

        return DF_YMD.format(LocalDate.now());

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
    public static LocalDateTime transformLocalTimeToLocalDateTime(LocalTime localTime) {

        LocalDate localDate = LocalDate.now();
        return LocalDateTime.of(localDate, localTime);

    }

    /**
     * 将时间字符串转成LocalTime格式，格式为"HH:mm"，例如"05:30"
     *
     * @param str 时间字符串
     * @return LocalTime
     */
    public static LocalTime transformStrToLocalTime(String str) {

        if (StringUtil.isBlank(str)) {
            return null;
        }

        try {
            return LocalTime.parse(str, DF_HM);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 将时间转成字符串格式，格式为"HH:mm"，例如"05:30"
     *
     * @param localDateTime 时间
     * @return String
     */
    public static String turnLocalDateTime2Hm(LocalDateTime localDateTime) {

        if (null == localDateTime) {
            return null;
        }

        try {
            return localDateTime.format(DF_HM);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 将时间字符串转成LocalDateTime格式，格式为"yyyy-MM-dd HH:mm:ss"，例如2018-06-28 11:36:30
     *
     * @param str 时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime transformStrToLocalDateTime(String str) {

        if (StringUtil.isBlank(str)) {
            return null;
        }

        try {
            return LocalDateTime.parse(str, DF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 获取当前时间，格式为"yyyy-MM-dd HH:mm:ss"，例如2018-06-28 11:36:30
     *
     * @return String
     */
    public static String getCurrentLocalDateTime() {

        return DF.format(LocalDateTime.now());

    }


    /**
     * 获取当前时间，格式为"HH:mm:ss"，例如11:36:30
     *
     * @return String
     */
    public static String getCurrentLocalTime() {

        return DF_HMS.format(LocalTime.now());

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


}
