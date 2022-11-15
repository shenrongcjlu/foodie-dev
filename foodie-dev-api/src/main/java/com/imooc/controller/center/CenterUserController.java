package com.imooc.controller.center;

import com.imooc.LoginContext;
import com.imooc.ResultDTO;
import com.imooc.center.dto.request.UserUpdateRequestDTO;
import com.imooc.portal.dto.UserDTO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/15 23:34
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

    private static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspace"
            + File.separator + "images"
            + File.separator + "foodie"
            + File.separator + "faces";

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ResultDTO<UserDTO> update(@RequestBody UserUpdateRequestDTO param, HttpServletRequest request, HttpServletResponse response) {
        UserDTO user = centerUserService.updateUserInfo(param);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return ResultDTO.success(user);
    }

    @ApiOperation("上传用户头像")
    @PostMapping("/uploadFace")
    public ResultDTO<Void> uploadFace(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String faceFilePath = IMAGE_USER_FACE_LOCATION + File.separator + LoginContext.getUserId();
        return ResultDTO.success();
    }

}
