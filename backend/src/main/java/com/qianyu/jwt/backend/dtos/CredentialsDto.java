package com.qianyu.jwt.backend.dtos;

// record is immutable
// this is just for reception, it won't edit the content field by filed
public record CredentialsDto(String login, char[] password) {

}
