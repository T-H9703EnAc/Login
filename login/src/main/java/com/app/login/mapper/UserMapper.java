package com.app.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.app.login.model.User;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
}
