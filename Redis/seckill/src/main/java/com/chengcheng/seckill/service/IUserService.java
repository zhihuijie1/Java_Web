package com.chengcheng.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengcheng.seckill.pojo.User;
import com.chengcheng.seckill.utils.Result;
import com.chengcheng.seckill.vo.LoginVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2024-03-25
 */
public interface IUserService extends IService<User> {

    Result doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
