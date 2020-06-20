package cn.dbdj1201.cos.cap5;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yz1201
 * @date 2020-06-20 18:35
 **/
@Slf4j(topic = "c.Test062002")
public class Test062002 {

    static volatile boolean initialized = false;

    public static void main(String[] args) {
        init();

        for (int i = 0; i < 10000; i++) {
            new Thread(Test062002::init, "t-" + i);
        }
    }

    static void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }

    private static void doInit() {
        log.debug("do Init");
    }


}
