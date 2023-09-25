package com.qianyu.jwt.backend.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String nom;
    private String prenom;
    private String userName;
    private String password;
}