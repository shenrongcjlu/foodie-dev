package com.imooc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 说明：war包启动类
 *
 * @author: shenrong
 * @date: 2022/12/10 15:56
 */
public class WarStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向application启动类
        return builder.sources(Application.class);
    }
}
