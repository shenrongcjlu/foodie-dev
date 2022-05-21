package com.imooc;

import lombok.Data;

/**
 * 说明 : 返回参数
 *
 * @author 沈荣
 * @date 2022/5/21 17:34
 */
@Data
public class ResultDTO<T> {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    public static <T> ResultDTO<T> build(Integer status, String msg, T data) {
        return new ResultDTO<>(status, msg, data);
    }

    public static <T> ResultDTO<T> build(Integer status, String msg, T data, String ok) {
        return new ResultDTO<>(status, msg, data, ok);
    }
    
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(data);
    }

    public static <T> ResultDTO<T> success() {
        return new ResultDTO<>(null);
    }
    
    public static <T> ResultDTO<T> fail(String msg) {
        return new ResultDTO<>(500, msg, null);
    }
    
    public static <T> ResultDTO<T> fail(T data) {
        return new ResultDTO<>(501, "error", data);
    }
    
    public ResultDTO() {

    }

    public ResultDTO(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public ResultDTO(Integer status, String msg, T data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultDTO(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
