package cn.dbdj1201.springcode.test;

import cn.dbdj1201.springcode.aop.MathCalculator;
import cn.dbdj1201.springcode.config.MainConfig;
import cn.dbdj1201.springcode.config.MainConfigOfAOP;
import cn.dbdj1201.springcode.entity.Person;
import cn.dbdj1201.springcode.entity.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-02 10:32
 **/
@Slf4j(topic = "c.BeanTest070201")
public class BeanTest070201 {

    private static ApplicationContext ac;

    static {
        ac = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
    }

    @Test
    public void test0() {
//        System.out.println(ac.getBean("person"));
//        Person bean = (Person) ac.getBean("person");
//        System.out.println(bean);
//        for (String name : ac.getBeanNamesForType(Person.class)) {
//            log.debug("name - {}", name);
//        }
//        bean.setApplicationContext();


//        log.debug("main ac {}", ac);

    }

    @Test
    public void test1() {
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<UserModel> userModels = jt.query("select * from tb_user", new BeanPropertyRowMapper<>(UserModel.class));

        userModels.forEach(System.out::println);
    }

    @Test
    public void test2(){
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            log.debug("{}",beanDefinitionName);
        }

//        System.out.println(ac.getBean("&colorFactoryBean"));
//        System.out.println(ac.getBean("colorFactoryBean"));
    }

    @Test
    public void test3(){
        MathCalculator mathCalculator = (MathCalculator) ac.getBean("mathCalculator");
        System.out.println(mathCalculator.div(5, 0));
    }

}
