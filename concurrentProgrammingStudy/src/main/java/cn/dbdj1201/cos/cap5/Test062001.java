package cn.dbdj1201.cos.cap5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-20 17:33
 **/
@Slf4j(topic = "c.Test062001")
public class Test062001 {
    public static void main(String[] args) {
//        log.debug("?");
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> log.debug("obj->{}", SingletonDemo.getInstance()), "thread-" + i).start();
//            new Thread(() -> log.debug("obj->{}", new Object()), "thread-" + i).start();
//        }

        test0();
    }

    private static int x;

    private static void test0() {
        Thread t2 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.debug("x->{}", x);
                    break;
                }
            }
        }, "t2");

        t2.start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                x = 10;
                t2.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        while (!t2.isInterrupted()) {
            Thread.yield();
        }
        log.debug("x->{}", x);

    }

}
