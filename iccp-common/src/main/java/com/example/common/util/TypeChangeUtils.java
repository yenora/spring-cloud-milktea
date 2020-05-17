package com.example.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: yaos
 * @Date: 2020-5-10 11:52
 * @Version：V1.0
 **/
public class TypeChangeUtils {

    public static String[] longToString(Long[] longArray) {
        if (longArray == null || longArray.length < 1) {
            return null;
        }
        String[] stringArray = new String[longArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            try {
                stringArray[i] = String.valueOf(longArray[i]);
            } catch (NumberFormatException e) {
                stringArray[i] = null;
            }
        }
        return stringArray;
    }

    public static Long[] StringArray2LongArray(String[] stringArray) {
        List<Long> list = new ArrayList<>();
        for (String str : stringArray) {
            try {
                list.add(Long.parseLong(str));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return list.toArray(new Long[list.size()]);
    }

    public static void main(String[] args) {
//        String[] s = {"1", "2", "3"};
//        for (Long num : StringArray2LongArray(s)) {
//            System.out.print(num + " ");
//        }

        Long[] l = {1L, 2L, 3L};
        for (String str : longToString(l)) {
            System.out.print(str);
        }
    }
}
