package com.qianyu.jwt.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserAuthProvider userAuthProvider;
		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(
								 "/register","/login","/messages","/users","/users/**"
								)
								.permitAll()
								.requestMatchers("/participants/**")
								.authenticated()
								)
				.httpBasic(Customizer.withDefaults()).build();
	}
}
