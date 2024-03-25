package com.chengcheng.seckill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengcheng.seckill.exception.GlobalException;
import com.chengcheng.seckill.pojo.User;
import com.chengcheng.seckill.mapper.UserMapper;
import com.chengcheng.seckill.service.IUserService;
import com.chengcheng.seckill.utils.MD5;
import com.chengcheng.seckill.utils.Result;
import com.chengcheng.seckill.utils.ResultCodeEnum;
import com.chengcheng.seckill.vo.LoginVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        //先把手机号码，密码拿出来
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //根据手机号码从数据库取出对应的用户
        User user = userMapper.selectById("mobile");
        if (user == null) {
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        //有当前的用户。然后进行密码校对
        if (!MD5.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        //用户名与密码全部正确
        //生成cookie

        //将用户信息存入redis

    }
}
