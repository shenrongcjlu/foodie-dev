package com.imooc.controller.center;

import com.imooc.center.dto.CenterUserDto;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.resouce.FileUploadProperties;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:38
 */
@Slf4j
@Api(value = "用户信息相关接口", tags = "用户信息相关接口")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private FileUploadProperties fileUploadProperties;
    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            String userId,
            @RequestBody CenterUserDto centerUserDto,
            HttpServletRequest request,
            HttpServletResponse response) {
        Users user = centerUserService.updateUserInfo(userId, centerUserDto);
        // 更新cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("/uploadFace")
    public IMOOCJSONResult uploadFace(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            String userId,
            @ApiParam(name = "file", value = "用户头像", required = true)
            @NotBlank(message = "文件不能为空")
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {

        centerUserService.uploadFace(userId, file, fileUploadProperties.getImageUserFaceLocation());



        return IMOOCJSONResult.ok();
    }

}
