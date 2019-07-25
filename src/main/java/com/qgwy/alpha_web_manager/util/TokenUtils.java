package com.qgwy.alpha_web_manager.util;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TokenUtils {
    private static String randString = "0123456789";//随机产生只有数字的字符串 private String
    private static Random random = new Random();
    private static final int CAPACITY = 4;
    private static final String SALT = "b32d075a25960ebcb0cad9da49861612";

    /**
     * token = username + "&" + 三次md5（当前日期的yyyyMMddHHmmss+四位随机数)
     * @return
     */
    public static String generatorToken(String username) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = sdf.format(new Date());
        String text = now + randomNum(CAPACITY);
        //三次md5
        String token = username + "&" + MD5.md5(text,SALT);
        return token;
    }

    /**
     * 生成n位随机数的字符串
     * @param n
     * @return
     */
    public static String randomNum(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            String rand = getRandomString(random.nextInt(randString.length()));
            result += rand;
        }
        return result;
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    public static void main(String[] args) {
        String s = generatorToken("admin");
        System.out.println(s);
    }
}
