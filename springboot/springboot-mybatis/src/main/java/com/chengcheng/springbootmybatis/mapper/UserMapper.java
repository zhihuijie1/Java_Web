package com.chengcheng.springbootmybatis.mapper;

import com.chengcheng.springbootmybatis.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from t_user")
    public List<User> findAllUser();
}
