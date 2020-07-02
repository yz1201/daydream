package cn.dbdj1201.springcode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;

/**
 * @author yz1201
 * @date 2020-07-02 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j(topic = "c.Person")
public class Person implements ApplicationContextAware {
    private String name;
    private Integer age;

    public void init() {
        log.debug("person init");
    }

    public void destroy() {
        log.debug("person destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        log.debug("create bean ac - {}",applicationContext);
    }
}
