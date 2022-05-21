package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 18:23
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
