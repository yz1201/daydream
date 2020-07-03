package cn.dbdj1201.springcode.config;

import cn.dbdj1201.springcode.bean.Color;
import cn.dbdj1201.springcode.bean.ColorFactoryBean;
import cn.dbdj1201.springcode.condition.LinuxCondition;
import cn.dbdj1201.springcode.condition.MyImportSelector;
import cn.dbdj1201.springcode.condition.MyImportingBeanDefinitionRegister;
import cn.dbdj1201.springcode.condition.WindowsCondition;
import cn.dbdj1201.springcode.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @author yz1201
 * @date 2020-07-02 10:23
 **/
@Slf4j(topic = "c.MainConfig")
@Configuration
//@ComponentScan("cn.dbdj1201.springcode")
//@PropertySource("classpath:class.txt")
//@Import({MyImportSelector.class, Color.class, MyImportingBeanDefinitionRegister.class})
public class MainConfig {

    @Value("${os.name}")
    private String osName;

    @Bean("yz120101")
    @Conditional(WindowsCondition.class)
    public Person person01() {
//        log.debug("osName - {}", osName);
        return new Person("root", 22);
    }

    @Bean("yz120102")
    @Conditional(LinuxCondition.class)
    public Person person02() {
        return new Person("root", 22);
    }

//    @Bean("yz120103")
//    public Person person03() {
//        return new Person("root", 22);
//    }
//
//    @Bean("yz120104")
//    public Person person04() {
//        return new Person("root4", 44);
//    }


    @Bean
    ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
