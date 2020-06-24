package cn.dbdj1201.demo.mapper;

import cn.dbdj1201.demo.DemoApplication12010;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yz1201
 * @date 2020-06-24 11:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication12010.class)
public class Test062401 {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void test() {
//        template.
    }
}
