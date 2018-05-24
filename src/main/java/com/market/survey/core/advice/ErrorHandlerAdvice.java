package com.market.survey.core.advice;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




@ControllerAdvice
public class ErrorHandlerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerAdvice.class);
	private static final String EXCEPTION_MESSAGE = "ERROR";
	private static final String NULL_MESSAGE="NULL ERROR";
	private static final String ACCESS_DENIED="ACCES DENIED";


	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> nullError(NullPointerException exception, Locale locale) {
		logger.error("ERROR: " + exception);
		HttpError httpError = new HttpError(HttpStatus.METHOD_FAILURE.value(), Exception.class.getName(), NULL_MESSAGE);
		return ResponseEntity.badRequest().body(httpError);
	}
	
	@ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
	public ResponseEntity<?> accessDenied(AccessDeniedException exception, Locale locale) {
		logger.error("ERROR: " + exception);
		HttpError httpError = new HttpError(HttpStatus.METHOD_FAILURE.value(), Exception.class.getName(), ACCESS_DENIED);
		return ResponseEntity.badRequest().body(httpError);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> uncaughtException(Exception exception) {
		logger.error("ERROR: " + exception);
		HttpError httpError = new HttpError(HttpStatus.METHOD_FAILURE.value(), Exception.class.getName(), EXCEPTION_MESSAGE);
		
		return ResponseEntity.badRequest().body(httpError);
	}
}
