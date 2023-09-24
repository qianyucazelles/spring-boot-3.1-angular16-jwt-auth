package com.qianyu.jwt.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.qianyu.jwt.backend.dtos.SignUpDto;
import com.qianyu.jwt.backend.dtos.UserDto;
import com.qianyu.jwt.backend.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toUserDto(User user);
	
	@Mapping(target = "password", ignore = true)
	User signUpToUser(SignUpDto signUpDto);

}
