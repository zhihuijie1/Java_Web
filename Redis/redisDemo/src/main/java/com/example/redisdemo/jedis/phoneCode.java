package com.example.redisdemo.jedis;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;

//手机验证码
public class phoneCode {

    //1：随机生成6位数字验证码
    public String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int nub = random.nextInt(10);
            code += nub;
        }
        System.out.println(code);
        return code;
    }

    //2：每个手机每天只能发送三次，验证码放到redis中，设置过期时间120
    public String verifcode(String phoneNub) {
        //每个手机每天只能发送三次
        Jedis jedis = new Jedis("192.168.136.138", 6379);
        String key = "verifcode" + phoneNub + "fuck";
        String codeKey = "code" + phoneNub + "code";
        if (jedis.get(key) == null) {
            jedis.setex(key, 60 * 60 * 24, "1");
        } else if (Integer.parseInt(jedis.get(key)) <= 2) {
            jedis.incr(key);
        } else {
            System.out.println("一天只能发送三次，现在已经超出次数");
            jedis.close();
            return null;
        }
        //将验证码放入redis中
        String code = getCode();
        jedis.setex(codeKey, 120, code);
        jedis.close();
        return code;
    }

    //3：检验
    public void getRedisCode(String phoneNub, String code) {
        Jedis jedis = new Jedis("192.168.136.138", 6379);
        String codekey = "code" + phoneNub + "code";
        String rediscode = jedis.get(codekey);
        if (rediscode == null || !rediscode.equals(code)) {
            System.out.println("失败");
        } else {
            System.out.println("成功");
        }
        jedis.close();
    }

    @Test
    public void test() {
        String c = verifcode("1210");
        getRedisCode("1210", c);
    }

}
