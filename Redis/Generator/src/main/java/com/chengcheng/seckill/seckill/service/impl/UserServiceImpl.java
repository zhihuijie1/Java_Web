package com.chengcheng.seckill.seckill.service.impl;

import com.chengcheng.seckill.seckill.pojo.User;
import com.chengcheng.seckill.seckill.mapper.UserMapper;
import com.chengcheng.seckill.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 橙橙
 * @since 2024-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
