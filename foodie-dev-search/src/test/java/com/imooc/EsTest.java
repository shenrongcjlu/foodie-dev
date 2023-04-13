package com.imooc;

import com.imooc.pojo.Stu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testAddIndex() {
        Stu stu = new Stu();
        stu.setAge(18);
        stu.setName("shenrong");
        stu.setId(1L);

        IndexQuery build = new IndexQueryBuilder().withObject(stu).build();
        elasticsearchTemplate.index(build);
    }

}
