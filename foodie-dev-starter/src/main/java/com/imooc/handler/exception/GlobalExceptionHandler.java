package com.imooc.handler.exception;

import com.imooc.ResultDTO;
import com.imooc.exception.BizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResultDTO<String> handleBizException(BizException e) {
        return ResultDTO.fail(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultDTO<String> handleUploadException(MaxUploadSizeExceededException e) {
        return ResultDTO.fail("上传文件大小不能超过1MB");
    }
}
