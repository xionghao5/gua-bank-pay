package com.gua.guapay.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class PayNumberUtil {
    /**
     * 银行卡前缀
     */
    private final static String BANK_CARD_PRE = "601000";

    private final static Random random = new Random();

    private PayNumberUtil() {
    }

    public static String generateBankcardNumber() {
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        return BANK_CARD_PRE + milliSecond + getRandomString();
    }

    private static String getRandomString() {
        String ranStr = random.nextInt(100000) + "";
        while (true) {
            if (ranStr.length() < 8) {
                ranStr += random.nextInt(10);
            } else {
                break;
            }
        }
        return ranStr;
    }

    public static void main(String[] args) {
        while (true) {
            String randomString = getRandomString();
            System.out.println(randomString);
            if (randomString.length() != 8) {
                break;
            }
        }
        System.out.println("fuck");
    }
}
