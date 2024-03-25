package com.chengcheng.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    public static String md5(String str) {
        return DigestUtils.md2Hex(str);
    }

    public static final String salt = "1a2b3c4d5e6fghn845x";

    //inputPassToFromPass
    public static String inputPassToFromPass(String inputPass) {
        //从盐里面挑几个数字掺进去
        String str = salt.charAt(2) + salt.charAt(4) + salt.charAt(6) + inputPass + salt.charAt(8);
        return md5(str);
    }

    //formPassToDBPass
    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(1) + salt.charAt(3) + salt.charAt(5) + formPass + salt.charAt(7);
        //String str = salt.charAt(2) + salt.charAt(4) + salt.charAt(6) + formPass + salt.charAt(8);
        return md5(str);
    }

    //inputPassToDBPass
    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassToFromPass(inputPass);
        String dbPass = formPassToDBPass(formPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        String formPass = inputPassToFromPass("1234567890");
        System.out.println(formPass);
        System.out.println(formPassToDBPass(formPass, "1a2b3c4d5e6fghn845x"));
        System.out.println(inputPassToDBPass("1234567890", "1a2b3c4d5e6fghn845x"));
    }
}
