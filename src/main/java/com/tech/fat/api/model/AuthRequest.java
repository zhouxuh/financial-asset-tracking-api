package com.tech.fat.api.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}
