package com.app.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.login.mapper.UserMapper;
import com.app.login.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
