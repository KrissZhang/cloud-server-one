package com.self.cloudserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String dateStr){
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            logger.error("时间转换异常：", e);
            return null;
        }
    }

}
