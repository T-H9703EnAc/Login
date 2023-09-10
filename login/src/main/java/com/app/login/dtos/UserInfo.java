package com.app.login.dtos;

import lombok.Data;

@Data
public class UserInfo {
    private String username;

    public UserInfo(String username) {
        this.username = username;
    }
}
