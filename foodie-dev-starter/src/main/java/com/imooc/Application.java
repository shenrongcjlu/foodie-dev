package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 18:23
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"org.n3r.idworker", "com.imooc"})
@PropertySource("classpath:file-upload-dev.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
