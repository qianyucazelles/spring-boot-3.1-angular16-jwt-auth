package com.qianyu.jwt.backend.config;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.qianyu.jwt.backend.dtos.UserDto;
import com.qianyu.jwt.backend.entities.User;
import com.qianyu.jwt.backend.exceptions.AppException;
import com.qianyu.jwt.backend.mappers.UserMapper;
import com.qianyu.jwt.backend.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class UserAuthProvider {
	
	private final UserRepository userRepository;
	
	private final UserMapper userMapper;
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	
	// this avoid the raw secret key available in jpa
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(UserDto userDto) {
		Date now = new Date();
		Date validity = new Date(now.getTime()+3_600_000); //1HR
		return JWT.create()
				.withIssuer(userDto.getLogin())
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withClaim("nom", userDto.getNom())
				.withClaim("prenom", userDto.getPrenom())
				.sign(Algorithm.HMAC256(secretKey));
	}
	
	public Authentication validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		
		UserDto user = UserDto.builder()
				.login(decoded.getIssuer())
				.nom(decoded.getClaim("nom").asString())
				.prenom(decoded.getClaim("prenom").asString())
				.build();
		
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
				
		
	}
	public Authentication validateTokenStrongly(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		
		User user = userRepository.findByLogin(decoded.getIssuer())
		.orElseThrow(()-> new AppException("User doesn't exist", HttpStatus.NOT_FOUND));
	
		
		return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());
				
		
	}
	
}
