package com.springboot.chat.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.chat.controller.model.CommonErrorResponse;

import jakarta.servlet.http.HttpSession;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private HttpSession session;
    
    /**
     * Exceptionがthrowされた場合のエラーレスポンス返却.
     * 
     * @param ex
     * @param request
     * @return
     */
    // もう少し細かくException設計が必要
    @ExceptionHandler(Exception.class)
    public final CommonErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        session.removeAttribute("user");
        CommonErrorResponse response = new CommonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return response;
    }
}