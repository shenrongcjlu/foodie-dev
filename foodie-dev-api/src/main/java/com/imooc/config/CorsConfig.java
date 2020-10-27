package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域的问题
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        // 2.设置是否发送cookie
        config.setAllowCredentials(true);
        // 3.设置允许请求的方式
        config.addAllowedMethod("*");
        // 4.设置允许的header
        config.addAllowedHeader("*");
        // 5.为url添加映射路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);
        // 6.返回重新定义好的source
        return new CorsFilter(configurationSource);
    }

}
