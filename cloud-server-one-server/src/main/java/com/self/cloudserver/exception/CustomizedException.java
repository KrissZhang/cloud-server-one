package com.self.cloudserver.exception;

/**
 * 用户自定义异常
 */
public class CustomizedException extends RuntimeException {

    public CustomizedException(String message) {
        super(message);
    }

}
