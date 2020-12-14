package com.imooc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/14 21:37
 */
@Data
public class OrderItemCommentDto implements Serializable {
    private static final long serialVersionUID = -684779448314632259L;

    private String commentId;
    private String itemId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private Integer commentLevel;
    private String content;
}
