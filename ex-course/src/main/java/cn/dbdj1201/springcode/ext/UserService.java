package cn.dbdj1201.springcode.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author yz1201
 * @date 2020-07-03 16:23
 **/
@Service
@Slf4j(topic = "c.UserServiceExt")
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        log.debug("event - {}", event);
    }
}
