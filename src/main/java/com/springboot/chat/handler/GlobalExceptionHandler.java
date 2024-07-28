package com.springboot.chat.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.chat.controller.model.CommonErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	// もう少し細かくException設計が必要
	@ExceptionHandler(Exception.class)
    public final CommonErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        CommonErrorResponse response = new CommonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return response;
    }
}