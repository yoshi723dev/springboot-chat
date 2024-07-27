package com.springboot.chat.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.chat.controller.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    @ExceptionHandler({Exception.class})
	protected ResponseEntity<Object> handleErrorResponseException(
			ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // リクエストのBodyのセット
        ErrorResponse response = new ErrorResponse(ex.getStatusCode().value(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, status, request);
	}
}