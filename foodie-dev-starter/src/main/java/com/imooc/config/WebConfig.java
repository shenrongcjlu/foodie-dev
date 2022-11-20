package com.imooc.config;

import com.imooc.factory.IntegerEnumConvertFactory;
import com.imooc.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 21:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.imageUserFaceLocation}")
    private String facePath;

    @Resource
    private IntegerEnumConvertFactory integerEnumConvertFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/") // swagger2
                .addResourceLocations("file:" + facePath + File.separator); // 映射图片资源路径
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(integerEnumConvertFactory);
    }
}
