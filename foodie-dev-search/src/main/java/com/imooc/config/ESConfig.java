package com.imooc.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/4/13 23:33
 */
@Configuration
public class ESConfig {

    /**
     * 解决netty引起的issue
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

}
