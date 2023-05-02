package com.DigitalTwin.project.service.impl;

import com.DigitalTwin.project.entity.User;
import com.DigitalTwin.project.mapper.UserMapper;
import com.DigitalTwin.project.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 11098
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-01 20:37:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




