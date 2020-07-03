package cn.dbdj1201.springcode.test;

import cn.dbdj1201.springcode.config.MainConfigOfAOP;
import cn.dbdj1201.springcode.entity.UserModel;
import cn.dbdj1201.springcode.service.IUserService;
import cn.dbdj1201.springcode.service.impl.UserService;
import cn.dbdj1201.springcode.tx.TxConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-03 13:41
 **/
@Slf4j(topic = "c.TxTest")
public class TxTest {
    private static final ApplicationContext ac;

    static {
        ac = new AnnotationConfigApplicationContext(TxConfig.class);
    }

//    @Autowired
//    private UserService userService;

    @Test
    public void test0() {
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<UserModel> userModels = jt.query("select * from tb_user", new BeanPropertyRowMapper<>(UserModel.class));

        userModels.forEach(System.out::println);
    }

    @Test
    public void test1() {
        for (String name : ac.getBeanDefinitionNames()) {
            log.debug("all beans - {}",name);
        }
//        IUserService service = (IUserService) ac.getBean("userService");
////        log.debug("userService {}", service);
////        log.debug("userService {}", this.userService);
//        service.insertUser(new UserModel("root5","root5"));
    }
}
