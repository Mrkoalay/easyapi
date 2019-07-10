package cn.easyapi.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author efanhome
 * @since 2019/1/29
 */
public class DateUtil {

    private final static DateTimeFormatter RELEASE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 获取昨天日期，格式yyyy-MM-dd
     *
     * @return 昨日
     */
    public static String getYesterday() {
        return LocalDate.now().minusDays(1).toString();
    }

    /**
     * 获取日期，格式yyyyMMdd
     *
     * @return 日期
     */
    public static String getSimpleDay(String day) {
        return LocalDate.parse(day).format(RELEASE_FORMATTER);
    }

    /**
     * 获取昨天日期，格式yyyyMMdd
     *
     * @return 昨日
     */
    public static String getSimpleYesterday() {
        return LocalDate.now().minusDays(1).format(RELEASE_FORMATTER);
    }

    /**
     * 获取昨天日期，格式java.util.Date
     *
     * @return 昨日
     */
    public static Date getDateYesterday() {
        return Date.from(LocalDateTime.now().plusDays(-1).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取昨天日期，格式java.util.Date
     *
     * @return 昨日
     */
    public static Date getDate(String day) {
        return Date.from(LocalDateTime.of(LocalDate.parse(day), LocalTime.of(0, 0))
                .atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取7天前的日期，格式yyyy-MM-dd
     * @param yesterday 昨天
     * @return 7天前
     */
    public static String getSevenDayBefore(String yesterday) {
         return LocalDate.parse(yesterday).minusDays(6).toString();
    }

    /**
     * 获取n月前的日期
     * @param nMonth 月数
     * @return n月前
     */
    public static Date getMonthBefore(int nMonth) {
        return Date.from(LocalDateTime.now().minusDays(nMonth * 30).atZone(ZoneId.systemDefault()).toInstant());
    }
}
