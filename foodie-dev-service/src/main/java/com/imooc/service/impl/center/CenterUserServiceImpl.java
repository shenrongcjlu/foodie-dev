package com.imooc.service.impl.center;

import com.imooc.center.dto.CenterUserDto;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:24
 */
@Slf4j
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUserInfo(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }

    @Transactional
    @Override
    public Users updateUserInfo(String userId, CenterUserDto centerUserDto) {
        Users users = new Users();
        BeanUtils.copyProperties(centerUserDto, users);
        users.setId(userId);
        users.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(users);
        return getUserInfo(userId);
    }

    @Override
    public String uploadFace(String userId, MultipartFile file, String imageUserFaceLocation) {
        // 用户上传文件路径加上userId的前缀
        String uploadPathPrefix = File.separator + userId;

        // 开始文件上传
        String fileName = file.getOriginalFilename();
        FileOutputStream fileOutputStream = null;
        String newFileName = "";
        if (StringUtils.isNotBlank(fileName)) {
            try {
                // 分割文件名
                String[] fileNameArr = fileName.split("\\.");
                // 获取文件后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];
                // 文件名重组，覆盖式上传（替换掉原有的图片）
                newFileName = "face-" + userId + "." + suffix;
                // 上传头像最终保存的位置
                String  finalFilePath = imageUserFaceLocation + uploadPathPrefix + File.separator + newFileName;

                File outFile = new File(finalFilePath);
                // 如果父目录不存在，创建父目录
                if (outFile.getParentFile() != null) {
                    outFile.getParentFile().mkdirs();
                }

                // 输出文件
                fileOutputStream = new FileOutputStream(outFile);
                InputStream inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(fileOutputStream);
            }
        }
        return newFileName;
    }

    @Transactional
    @Override
    public void updateUserFace(String userId, String faceUrl) {
        Users users = new Users();
        users.setId(userId);
        users.setFace(faceUrl);
        users.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(users);
    }
}
