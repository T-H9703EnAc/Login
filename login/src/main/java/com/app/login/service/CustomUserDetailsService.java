package com.app.login.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.login.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            throw new IllegalStateException("Username or PasswordHash is null or empty for user: " + username);
        }
        // UserDetailsオブジェクトを返す際に、Spring Securityが提供するUserクラスを利用できます
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), new ArrayList<>());
    }
    
}
