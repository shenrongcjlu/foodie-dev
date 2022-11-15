package com.imooc.portal.dto;

import lombok.Data;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 20:03
 */
@Data
public class CommentLevelCountsDTO {

    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;

}
