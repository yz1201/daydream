package cn.dbdj1201.springcode.condition;

import cn.dbdj1201.springcode.bean.Rainbow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yz1201
 * @date 2020-07-02 15:22
 **/
@Slf4j(topic = "c.MyImporting")
public class MyImportingBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        log.debug("i am here");

        if (registry.containsBeanDefinition("cn.dbdj1201.springcode.bean.Red")
                && registry.containsBeanDefinition("cn.dbdj1201.springcode.bean.Yellow")) {
            registry.registerBeanDefinition("rainBow",
                    new RootBeanDefinition(Rainbow.class));
        }
    }
}
