package com.tsp.belle.util;

import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniqueKey(){

        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
    /**
     * @param digit 位数百
     * @return 随机生成digit位数的数字度
     */
    public static long getNum(int digit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            if (i == 0 && digit > 1)
                str.append(new Random().nextInt(9) + 1);
            else
                str.append(new Random().nextInt(10));
        }
        return Long.valueOf(str.toString());
    }
}