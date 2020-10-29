package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        log.info("=================================");
        return "Hello World";
    }

}
