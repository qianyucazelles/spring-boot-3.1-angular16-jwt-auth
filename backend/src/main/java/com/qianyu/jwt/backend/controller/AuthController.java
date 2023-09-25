package com.qianyu.jwt.backend.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qianyu.jwt.backend.dtos.AuthResponseDto;
import com.qianyu.jwt.backend.dtos.LoginDto;
import com.qianyu.jwt.backend.dtos.RegisterDto;
import com.qianyu.jwt.backend.entities.Role;
import com.qianyu.jwt.backend.entities.UserEntity;
import com.qianyu.jwt.backend.repositories.RoleRepository;
import com.qianyu.jwt.backend.repositories.UserRepository;
import com.qianyu.jwt.backend.security.JWTGenerator;

@CrossOrigin
@RestController
@RequestMapping("")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUserName(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUserName(registerDto.getUserName());
        user.setNom(registerDto.getNom());
        user.setPrenom(registerDto.getPrenom());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}