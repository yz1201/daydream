package cn.dbdj1201.demo.service.impl;

import cn.dbdj1201.demo.DemoApplication12010;
import cn.dbdj1201.demo.entities.ClassModel;
import cn.dbdj1201.demo.service.IClassModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yz1201
 * @date 2020-06-18 11:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication12010.class)
public class ClassModelServiceTest {

    @Autowired
    private IClassModelService service;

    @Test
    public void findAll() {
        List<ClassModel> classModels = service.findAll();
//        System.out.println(classModels);
        classModels.forEach(System.out::println);
    }
}