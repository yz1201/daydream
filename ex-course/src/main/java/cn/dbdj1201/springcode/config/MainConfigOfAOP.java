package cn.dbdj1201.springcode.config;

import cn.dbdj1201.springcode.aop.LogAspect;
import cn.dbdj1201.springcode.aop.MathCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yz1201
 * @date 2020-07-02 16:08
 **/
@Slf4j(topic = "c.MainConfigOfAOP")
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
