package cn.dbdj1201.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yz1201
 * @date 2020-06-18 11:14
 **/
@SpringBootApplication
@MapperScan("cn.dbdj1201.demo.mapper")
public class DemoApplication12010 {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication12010.class, args);
    }
}
