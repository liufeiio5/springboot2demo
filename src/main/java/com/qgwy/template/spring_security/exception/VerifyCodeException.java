package com.qgwy.template.spring_security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码异常
 */
public class VerifyCodeException extends AuthenticationException {

    public VerifyCodeException(String msg) {
        super(msg);
    }

    public VerifyCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}