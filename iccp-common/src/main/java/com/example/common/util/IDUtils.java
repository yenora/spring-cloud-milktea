package com.example.common.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class IDUtils {
    public IDUtils() {
    }

    public static Long genRandomId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(999);
        String str = millis + String.format("%03d", end3);
        return Long.parseLong(str);
    }

    public static String random(int bit) {
        Preconditions.checkArgument(bit > 0, "位数必须大于0");
        return RandomStringUtils.randomNumeric(bit);
    }


    public static String randomStr(int bit) {
        Preconditions.checkArgument(bit > 0, "位数必须大于0");
        return RandomStringUtils.randomAlphanumeric(bit);
    }

    public static void main(String[] args) {
        System.out.println("-------------");
        System.out.println(IDUtils.genRandomId());
        System.out.println("-------------");
        System.out.println(IDUtils.random(5));
        System.out.println("-------------");
        System.out.println(IDUtils.randomStr(5));
        System.out.println("-------------");
    }
}
