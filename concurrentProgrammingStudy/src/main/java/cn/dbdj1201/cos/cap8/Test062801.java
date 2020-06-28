package cn.dbdj1201.cos.cap8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: yz1201
 * @date: 2020-06-28 15:51
 */
@Slf4j(topic = "c.Test062801")
public class Test062801 {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    s.acquire();
                    log.debug("i am a thread running");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug(" end ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }).start();
        }
    }
}
