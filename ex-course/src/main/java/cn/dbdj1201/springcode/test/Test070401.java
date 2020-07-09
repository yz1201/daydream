package cn.dbdj1201.springcode.test;

import cn.dbdj1201.springcode.config.MainConfigOfAOP;
import cn.dbdj1201.springcode.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yz1201
 * @date 2020-07-04 11:42
 **/
@Slf4j(topic = "c.Test070401")
public class Test070401 {

    private static ApplicationContext ac;

    static {
        ac = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    @Test
    public void test0() {
//        for (String name : ac.getBeanDefinitionNames()) {
//            log.debug("{}", name);
//        }

//        ac.get
    }
}
