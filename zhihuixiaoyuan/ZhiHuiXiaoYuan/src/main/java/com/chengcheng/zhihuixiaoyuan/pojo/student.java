package com.chengcheng.zhihuixiaoyuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_student")
public class student {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String sno;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String introducation;
    private String portrait_path;
    private String clazz_name;
}
