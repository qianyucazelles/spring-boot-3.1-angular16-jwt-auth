package com.qianyu.jwt.backend.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qianyu.jwt.backend.config.UserAuthProvider;
import com.qianyu.jwt.backend.dtos.CredentialsDto;
import com.qianyu.jwt.backend.dtos.SignUpDto;
import com.qianyu.jwt.backend.dtos.UserDto;
import com.qianyu.jwt.backend.services.UserService;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;
	
	


	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
		UserDto user = userService.register(signUpDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.created(URI.create("/users/"+user.getId())).body(user);
	}

}
