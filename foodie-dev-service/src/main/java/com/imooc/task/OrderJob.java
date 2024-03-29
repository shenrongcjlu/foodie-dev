package com.imooc.task;

import com.imooc.service.portal.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/11/13 21:35
 */
@Component
public class OrderJob {

    @Resource
    private OrderService orderService;

//    @Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder() {
        System.out.println("执行定时任务");
        orderService.closeTimeoutOrder();
    }

}
