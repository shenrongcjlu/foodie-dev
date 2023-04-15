package com.imooc.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/4/13 23:39
 */
@Data
public class Stu {

    @Id
    private Long id;

    @Field
    private String name;

    @Field
    private Integer age;

}
