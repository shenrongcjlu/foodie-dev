package com.imooc.dto;

import lombok.Data;

import java.util.Date;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 21:06
 */
@Data
public class CommentDTO {
    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
