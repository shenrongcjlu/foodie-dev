package com.imooc;

import com.imooc.pojo.Stu;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
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
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testAddIndex() throws IOException {
        Stu stu = new Stu();
        stu.setAge(18);
        stu.setName("shenrong");
        stu.setId(1L);

        Map<String, String> map = new HashMap<>();
        map.put("name", "shenrong");


        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(stu)
                .build();
        String documentId = elasticsearchOperations.save(stu);
    }

}
