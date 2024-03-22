package com.chengcheng.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//添加MapperScan注解的功能是：直接扫描指定包中的所有mapper接口，让spring一次性实现他们的实现类，并在以后的操作中可以直接注入。
//不用这个注解，需要在每一个Mapper接口上添加@Mapper注解来让spring识别
@MapperScan("com.chengcheng.seckill.pojo")
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
