package com.imooc.config;

import com.imooc.factory.IntegerEnumConvertFactory;
import com.imooc.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 21:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private IntegerEnumConvertFactory integerEnumConvertFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(integerEnumConvertFactory);
    }
}
