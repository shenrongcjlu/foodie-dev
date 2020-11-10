package com.imooc.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用于展示商品评价数量数VO
 * @author shenrong
 * @version 1.0
 * @date 2020/11/5 23:42
 */
@Data
@Builder
public class CommentLevelVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
