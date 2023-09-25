package com.qianyu.jwt.backend.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MessageController {
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/usermessages")
	public ResponseEntity<List<String>> userMmessages(){
		return ResponseEntity.ok(Arrays.asList("hello","user"));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/adminmessages")
	public ResponseEntity<List<String>> adminMessages(){
		return ResponseEntity.ok(Arrays.asList("hello","admin"));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/adminmessages2")
	public ResponseEntity<List<String>> adminMessagesTwo(){
		return ResponseEntity.ok(Arrays.asList("hello","admin 2"));
	}
	

}
