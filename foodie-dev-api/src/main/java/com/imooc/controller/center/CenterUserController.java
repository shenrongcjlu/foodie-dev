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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/15 23:34
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/userInfo")
@Slf4j
public class CenterUserController {

    @Value("${file.imageUserFaceLocation}")
    private String facePath;
    @Value("${file.imageServerUrl}")
    private String imageServerUrl;

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
    public ResultDTO<UserDTO> uploadFace(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String faceFilePath = facePath + File.separator + LoginContext.getUserId();
        // 进行文件上传
        String fileName = "face_" + LoginContext.getUserId() + "_" + file.getOriginalFilename();
        File outFile = new File(faceFilePath + File.separator + fileName);
        try {
            if (outFile.getParentFile() != null) {
                outFile.getParentFile().mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultDTO.fail("上传文件失败");
        }
        String faceUrl = imageServerUrl + LoginContext.getUserId() + "/" + fileName + "?timestamp=" + System.currentTimeMillis();
        UserDTO userDTO = centerUserService.updateUserFace(faceUrl);
        // 清除缓存
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userDTO), true);
        return ResultDTO.success(userDTO);
    }

}
