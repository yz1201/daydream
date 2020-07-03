package cn.dbdj1201.springcode.ext;

import cn.dbdj1201.springcode.bean.Red;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yz1201
 * @date 2020-07-03 14:57
 **/
@Slf4j(topic = "c.ExtConfig")
@Configuration
@ComponentScan("cn.dbdj1201.springcode.ext")
public class ExtConfig {

    @Bean
    public Red red(){
        return  new Red();
    }

}
