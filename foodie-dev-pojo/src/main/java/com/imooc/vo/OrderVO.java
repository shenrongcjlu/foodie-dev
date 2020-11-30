package com.imooc.vo;

import com.imooc.dto.MerchantOrdersDTO;
import lombok.Data;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/30 23:10
 */
@Data
public class OrderVO {

    private String orderId;
    private MerchantOrdersDTO merchantOrdersDTO;

}
