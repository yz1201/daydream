package cn.dbdj1201.jvm.cap2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-07-11 10:08
 **/
@Slf4j(topic = "c.Test071101")
public class Test071101 {

    public static void main(String[] args) {
        ClassLoader sys = ClassLoader.getSystemClassLoader();
        log.debug("{}", sys);
        log.debug("{}", sys.getParent());
        log.debug("{}", sys.getParent().getParent());
        Runtime.getRuntime().availableProcessors();
    }
}
