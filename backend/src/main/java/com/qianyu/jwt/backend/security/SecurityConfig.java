package com.qianyu.jwt.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(
								 "/register","/login","/users","/users/**"
								)
								.permitAll()
								.requestMatchers(
										"/usermessages",
										"/adminmessages",
										"/adminmessages2"
										)
								.authenticated()
								)
				.httpBasic(Customizer.withDefaults()).build();
	}
	
    

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}