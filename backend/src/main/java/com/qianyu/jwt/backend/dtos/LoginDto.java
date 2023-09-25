package com.qianyu.jwt.backend.dtos;

import lombok.Data;

// record is immutable
// this is just for reception, it won't edit the content field by filed
@Data
public class LoginDto {
    private String userName;
    private String password;
}
