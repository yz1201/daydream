package cn.dbdj1201.demo2.day05;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-06-19 16:31
 **/
@Slf4j(topic = "c.Test61903")
public class Test061903 {
    public static void main(String[] args) {
        log.debug("he he");
        Thread t1 = new Thread(()-> log.debug("main"));
        t1.start();
    }
}
