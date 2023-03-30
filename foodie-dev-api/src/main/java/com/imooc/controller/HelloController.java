package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/3/30 21:40
 */
@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
