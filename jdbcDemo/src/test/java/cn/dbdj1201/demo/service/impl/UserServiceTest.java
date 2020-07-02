package cn.dbdj1201.demo.service.impl;

import cn.dbdj1201.demo.DemoApplication12010;
import cn.dbdj1201.demo.mapper.UserModelMapper;
import cn.dbdj1201.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yz1201
 * @date 2020-06-18 14:55
 **/
@SpringBootTest(classes = DemoApplication12010.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void login() {
//        boolean login = userService.login("root", "root");
//        System.out.println(login);
        userService.findAll().forEach(System.out::println);
    }
}