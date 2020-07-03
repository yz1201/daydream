package cn.dbdj1201.springcode.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author yz1201
 * @date 2020-07-03 14:59
 **/
@Slf4j(topic = "c.MyBeanFactoryPostProfessor")
//@Component
public class MyBeanFactoryPostProfessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String name : beanFactory.getBeanDefinitionNames()) {
            log.debug("name - {} -{}", name, beanFactory.getBeanDefinitionCount());
        }

    }
}
