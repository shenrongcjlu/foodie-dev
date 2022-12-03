package com.imooc.center.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 22:04
 */
@Data
public class CenterCommentRespDTO {
    private String commentId;
    private String itemId;
    private String itemName;
    private String specName;
    private Integer commentLevel;
    private String content;
    private String itemImg;
    private Date createTime;
}
