package com.imooc.center.dto;

import lombok.Data;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 21:36
 */
@Data
public class CommentDTO {

    private String commentId;
    private String itemId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private Integer commentLevel;
    private String content;

}
