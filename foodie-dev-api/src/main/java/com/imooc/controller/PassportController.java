package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.request.UserRequestDTO;
import com.imooc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:34
 */
@Slf4j
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public ResultDTO<Boolean> usernameIsExist(@RequestParam @NotBlank(message = "userName不能为空") String userName) {
        return ResultDTO.success(userService.isUserExist(userName));
    }

    @PostMapping("/register")
    public ResultDTO<Void> register(@RequestBody @Valid UserRequestDTO param) {
        userService.createUser(param);
        return ResultDTO.success();
    }
}
