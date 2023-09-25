package com.qianyu.jwt.backend.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qianyu.jwt.backend.dtos.AuthResponseDto;
import com.qianyu.jwt.backend.dtos.LoginDto;
import com.qianyu.jwt.backend.repositories.RoleRepository;
import com.qianyu.jwt.backend.repositories.UserRepository;
import com.qianyu.jwt.backend.security.JWTGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServices {
	private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    
    public AuthResponseDto login(LoginDto loginDto) {
    	authenticationManager.authenticate(
    	        new UsernamePasswordAuthenticationToken(
    	            loginDto.getUserName(),
    	            loginDto.getPassword()
    	        )
    	    );
    	    var user = repository.findByEmail(request.getEmail())
    	        .orElseThrow();
    	    var jwtToken = jwtService.generateToken(user);
    	    var refreshToken = jwtService.generateRefreshToken(user);
    	    revokeAllUserTokens(user);
    	    saveUserToken(user, jwtToken);
    	    return AuthenticationResponse.builder()
    	        .accessToken(jwtToken)
    	            .refreshToken(refreshToken)
    	        .build();
    }
	

}
