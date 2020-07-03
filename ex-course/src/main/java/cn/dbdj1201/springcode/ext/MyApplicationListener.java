package cn.dbdj1201.springcode.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author yz1201
 * @date 2020-07-03 15:33
 **/
@Component
@Slf4j(topic = "c.MyApplicationListener")
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("event - {}", event);
    }
}
