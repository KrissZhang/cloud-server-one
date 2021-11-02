package com.self.cloudserver.util;

import java.util.Random;

/**
 * 随机值工具类
 */
public class RandomUtil {

    /**
     * 全部字符
     */
    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 字母字符
     */
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 数值字符
     */
    public static final String NUMBER_CHAR = "0123456789";

    public static Random random = new Random();

    /**
     * 获取指定位数的字母和数字随机值
     * @param length 随机值长度
     * @return 随机值
     */
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < length; i++) {
            buffer.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }

        return buffer.toString();
    }

    /**
     * 获取指定位数的纯数值随机值
     * @param length 随机值长度
     * @return 随机值
     */
    public static String getRandomNumber(int length) {
        StringBuffer buffer = new StringBuffer();
        int len = NUMBER_CHAR.length();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(len);
            buffer.append(NUMBER_CHAR.charAt(index));
        }

        return buffer.toString();
    }

}
