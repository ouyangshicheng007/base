package com.yimi.oysc.service.impl;

import com.yimi.oysc.entity.UserEntity;
import com.yimi.oysc.mapper.UserMapper;
import com.yimi.oysc.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
