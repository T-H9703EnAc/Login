package com.app.login.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt; 
}
