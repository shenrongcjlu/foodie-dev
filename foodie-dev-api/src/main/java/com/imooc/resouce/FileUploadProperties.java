package com.imooc.resouce;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/6 9:53
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev.properties")
public class FileUploadProperties {

    private String imageUserFaceLocation;

}
