package com.imooc.controller;

import com.imooc.Constants;
import com.imooc.portal.dto.UserDTO;
import com.imooc.service.portal.UserService;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/3/30 21:40
 */
@Controller
public class SSOController {

    @Resource
    private UserService userService;
    @Resource
    private RedisOperator redisOperator;

    @GetMapping("login")
    public String login(String returnUrl,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @PostMapping("doLogin")
    public String doLogin(String username,
                          String password,
                          Model model,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            model.addAttribute("errmsg", "用户名或密码不能为空");
            return "login";
        }


        UserDTO userDTO = userService.getUserByNameAndPassword(username, MD5Utils.getMD5Str(password));
        if (userDTO == null) {
            model.addAttribute("errmsg", "用户名或密码错误");
            return "login";
        }

        // 生成用户token
        String uniqueToken = UUID.randomUUID().toString().trim();
        userDTO.setUserUniqueToken(uniqueToken);
        redisOperator.set(Constants.USER_TOKEN + ":" + userDTO.getId(), JsonUtils.objectToJson(userDTO));

        // 生成用户ticket
        String userTicket = UUID.randomUUID().toString().trim();
        redisOperator.set(Constants.USER_TICKET + ":" + userTicket, userDTO.getId());
        addCookie(response, userTicket);

        // 生成临时ticket
        String userTmpTicket = UUID.randomUUID().toString().trim();
        redisOperator.set(Constants.USER_TMP_TICKET + ":" + userTmpTicket, MD5Utils.getMD5Str(userTicket), 600);

        return "login";
    }

    private void addCookie(HttpServletResponse response, String userTicket) {
        // ticket放到cookie中
        Cookie cookie = new Cookie(Constants.COOKIE_USER_TICKET, userTicket);
        cookie.setDomain("sso.com");
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
