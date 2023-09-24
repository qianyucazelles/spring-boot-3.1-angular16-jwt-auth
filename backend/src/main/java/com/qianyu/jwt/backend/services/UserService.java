package com.qianyu.jwt.backend.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.qianyu.jwt.backend.dtos.CredentialsDto;
import com.qianyu.jwt.backend.dtos.SignUpDto;
import com.qianyu.jwt.backend.dtos.UserDto;
import com.qianyu.jwt.backend.entities.User;
import com.qianyu.jwt.backend.exceptions.AppException;
import com.qianyu.jwt.backend.mappers.UserMapper;
import com.qianyu.jwt.backend.repositories.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}



	public UserDto login(CredentialsDto credentialsDto) {
		User user = this.userRepository.findByLogin(credentialsDto.login())
		.orElseThrow(()-> new AppException("Unknown user", HttpStatus.NOT_FOUND));
		
		if (passwordEncoder
				.matches(CharBuffer.wrap(credentialsDto.password()),
						user.getPassword()
						)){
			return userMapper.toUserDto(user);
		} else {
			throw new AppException("Incorrect password", HttpStatus.BAD_REQUEST);
		}
		
		
	}



	public UserDto register(SignUpDto signUpDto) {
		Optional<User> oUser = userRepository.findByLogin(signUpDto.login());
		
		if (oUser.isPresent()) {
			throw new AppException("Email is already used.", HttpStatus.BAD_REQUEST);
		}
		
		User user = userMapper.signUpToUser(signUpDto);
		
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
		
		userRepository.save(user);
		
		return userMapper.toUserDto(user);
	}

}
