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
    
    /**
     * ログイン後のセッション情報を各コントローラでチェックする.
     * 
     * @param joinPoint
     * @throws Exception
     */
    //execution(戻り値の型 パッケージ名.クラス名.関数名(引数))
    @Before("execution(* com.springboot.chat.controller.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) throws Exception {
        String noTarget = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
        if ("UserController.login".equals(noTarget)) {
            return;
        }
        
        if (session.getAttribute("user") == null) {
            throw new Exception("session error");
        }
    }
}
