package com.imooc.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/7 22:03
 */
@Data
@Builder
public class ItemCommentVO {

    private String commentLevel;
    private String content;
    private String specName;
    private Date createTime;
    private String userFace;
    private String nickname;

}
