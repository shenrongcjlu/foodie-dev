package com.imooc;

import com.imooc.pojo.Stu;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/4/13 23:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsTest {

    @Resource
    RestHighLevelClient highLevelClient;


    @Test
    public void testAddIndex() throws IOException {
        Stu stu = new Stu();
        stu.setAge(18);
        stu.setName("shenrong");
        stu.setId(1L);

        Map<String, String> map = new HashMap<>();
        map.put("name", "shenrong");


        IndexRequest request = new IndexRequest("spring-data")
                .id("afsdasdf")
                .source(map)
                ;

        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
    }

}
