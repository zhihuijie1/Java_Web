package com.chengcheng.zhihuixiaoyuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok提供的注解，自动提供get set toString方法
@NoArgsConstructor //lombok提供的注解，自动生成无参构造函数
@AllArgsConstructor //lombok提供的注解，自动生成包含所有属性的构造函数。
@TableName("tb_admin") //MyBatis-Plus提供的注解，将实体类与数据库表进行一一映射
public class Admin {
    //MyBatis-Plus提供的注解，用于指定实体类的主键属性。
    //value:哪个是主键
    //type:主键的生成策略，IdType.AUTO，表示主键由数据库自动生成。
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String portraitPath; //头像的图片路径
}
