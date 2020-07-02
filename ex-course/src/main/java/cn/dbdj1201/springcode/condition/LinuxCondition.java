package cn.dbdj1201.springcode.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author yz1201
 * @date 2020-07-02 14:49
 **/
@Slf4j(topic = "c.LinuxCondition")
public class LinuxCondition implements Condition {

//    @Autowired
//    private String osName;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
