package cn.dbdj1201.cos.cap8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yz1201
 * @date 2020-06-26 11:19
 **/
@Slf4j(topic = "c.TestTimer")
public class TestTimer {
    public static void main(String[] args) {
//        test0();
//        test1();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start..");
        pool.scheduleAtFixedRate(()->log.debug("hello"),1,1,TimeUnit.SECONDS);
    }

    private static void test1() {
        log.debug("begin working");
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(() -> {
            log.debug("task 1 begin");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 1 end");
        }, 1, TimeUnit.SECONDS);
        pool.schedule(() -> {
            log.debug("task 2 begin");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 2 end");
        }, 1, TimeUnit.SECONDS);
        pool.schedule(() -> {
            log.debug("task 3 begin");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 3 end");
        }, 1, TimeUnit.SECONDS);
    }

    private static void test0() {
        Timer timer = new Timer();
        log.debug("running");
        TimerTask timerTask = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("execute task 1");
                TimeUnit.SECONDS.sleep(2);
            }
        };

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("execute task 2");
            }
        };

        timer.schedule(timerTask, 1000L);
        timer.schedule(timerTask1, 1000L);
    }
}
