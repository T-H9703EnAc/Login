package com.app.login.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.login.dtos.LoginRequest;
import com.app.login.dtos.UserInfo;

@RestController
public class LoginController {
  @PostMapping("/api/login")
    public ResponseEntity<?> performLogin(@RequestBody LoginRequest loginRequest) {
        boolean isSuccess = true; // ここは実際の認証ロジックに基づいてtrueまたはfalseを設定
        return ResponseEntity.ok(Map.of("success", isSuccess));
    }

    @GetMapping("/api/user")
    public ResponseEntity<UserInfo> getCurrentUser() {
        UserInfo userInfo = new UserInfo("exampleUsername");
        return ResponseEntity.ok(userInfo);
    }  
}
