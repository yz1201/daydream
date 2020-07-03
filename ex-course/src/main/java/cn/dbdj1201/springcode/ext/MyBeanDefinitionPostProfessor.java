package cn.dbdj1201.springcode.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yz1201
 * @date 2020-07-03 15:17
 **/
@Slf4j(topic = "c.MyBeanDefinitionRegistry")
@Component
public class MyBeanDefinitionPostProfessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.debug("registry - {} - {}",registry.getBeanDefinitionCount(),registry.getBeanDefinitionNames());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.debug("{} - {}",beanFactory.getBeanDefinitionCount(),beanFactory.getBeanDefinitionNames());
    }
}
