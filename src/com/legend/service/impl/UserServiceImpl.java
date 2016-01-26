package com.legend.service.impl;

import org.springframework.stereotype.Service;

import com.legend.model.User;
import com.legend.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

}
