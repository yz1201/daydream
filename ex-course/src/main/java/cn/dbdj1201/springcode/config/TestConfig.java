package cn.dbdj1201.springcode.config;

import cn.dbdj1201.springcode.bean.Color;
import cn.dbdj1201.springcode.bean.Rainbow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author yz1201
 * @date 2020-07-04 11:43
 **/
@Configuration
@ComponentScan("cn.dbdj1201.springcode.bean")
public class TestConfig {

    @Bean
//    @Lazy
    public Color color() {
        return new Color();
    }

//    @Bean
//    public Rainbow rainbow() {
//        return new Rainbow();
//    }
}
