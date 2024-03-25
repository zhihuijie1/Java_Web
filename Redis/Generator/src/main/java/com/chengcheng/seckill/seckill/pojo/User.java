package com.chengcheng.seckill.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import 你自己的父类实体,没有就不用设置!;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 橙橙
 * @since 2024-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class User extends 你自己的父类实体,没有就不用设置! {

    private static final long serialVersionUID = 1L;

    private String nickname;

    /**
     * MD5(明文+salt)
     */
    private String password;

    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 登录次数
     */
    private Integer loginCount;


}
