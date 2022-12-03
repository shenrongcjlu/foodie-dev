package com.imooc.center.dto.response;

import lombok.Data;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 22:53
 */
@Data
public class UserOrderCountsRespDTO {

    private Integer waitPayCounts;
    private Integer waitDeliverCounts;
    private Integer waitReceiveCounts;
    private Integer waitCommentCounts;

}
