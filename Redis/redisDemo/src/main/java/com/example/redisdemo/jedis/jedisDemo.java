package com.example.redisdemo.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class jedisDemo {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.136.136", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.close();
    }

    @Test
    public void test1() {
        Jedis jedis = new Jedis("192.168.136.136", 6379);
        jedis.sadd("key1", "v1", "v2", "v3");
        Set<String> key1 = jedis.smembers("key1");
        System.out.println(key1);
        jedis.close();
    }
}
