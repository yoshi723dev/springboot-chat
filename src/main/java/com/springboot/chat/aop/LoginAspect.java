package com.springboot.chat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {
	
	@Autowired
	private HttpSession session;
	
	// 動いてない
    @Before("execution(* com.springboot.chat.controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint) throws Exception {
    	System.out.println(joinPoint.getSignature().getName());
    	if ("com.springboot.chat.controller.UserController.login".equals(joinPoint.getSignature().getName())) {
    		return;
    	}
    	
    	if (session.getAttribute("user") == null) {
    		throw new Exception("session error");
    	}
    }
}
