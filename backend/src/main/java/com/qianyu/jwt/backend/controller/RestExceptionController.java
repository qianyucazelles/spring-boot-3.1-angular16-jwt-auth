package com.qianyu.jwt.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianyu.jwt.backend.dtos.ErrorDto;
import com.qianyu.jwt.backend.exceptions.AppException;

@CrossOrigin
@ControllerAdvice
public class RestExceptionController {
	
	@ExceptionHandler(value= {AppException.class})
	@ResponseBody
	public ResponseEntity<ErrorDto> handleException(AppException ex){
		System.out.println(ex.getMessage());
		return ResponseEntity.status(ex.getHttpStatus())
				.body(new ErrorDto(ex.getMessage()));
		
	
	}
}
