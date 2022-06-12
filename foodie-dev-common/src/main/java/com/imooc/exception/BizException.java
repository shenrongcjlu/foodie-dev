package com.imooc.exception;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:05
 */
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }
}
